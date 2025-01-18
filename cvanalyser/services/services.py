import requests
from concurrent.futures import ThreadPoolExecutor
from dto import parse_postule, parse_post, parse_cv
from analyzer.CvAnalyzer import process_file
from config.settings import POSTULE_URL, CV_URL, POST_URL
import json

def fetch_and_process_data(postule_id):
    try:
        # Fetch postule data
        response = requests.get(POSTULE_URL + str(postule_id))
        response.raise_for_status()
        postule_data = response.json()
        postule = parse_postule(postule_data)
        # Define functions for concurrent fetching of CV and Post data
        def fetch_cv():
            response = requests.get(CV_URL + str(postule.etudiantId))
            response.raise_for_status()
            return parse_cv(response.json())
        def fetch_post():
            response = requests.get(POST_URL + str(postule.postId))
            response.raise_for_status()
            return parse_post(response.json())
        # Use ThreadPoolExecutor for concurrent requests
        with ThreadPoolExecutor() as executor:
            future_cv = executor.submit(fetch_cv)
            future_post = executor.submit(fetch_post)
            cv = future_cv.result()
            post = future_post.result()
        # Extract keywords and process the file
        keywords = [keyword.lower() for keyword in post.keyword]
        data = process_file(cv.path, keywords)
        return {
            "post_id": postule.id,
            "data": data
        }
        # Return combined data
    except requests.RequestException as e:
        return {"error": str(e)}, 500
