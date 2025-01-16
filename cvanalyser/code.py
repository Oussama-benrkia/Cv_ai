import mimetypes
import docx2txt
import fitz  # PyMuPDF for faster PDF processing
import os
import requests  # To fetch files from URLs
from concurrent.futures import ThreadPoolExecutor
from collections import Counter
import re
from io import BytesIO
import tempfile

def extract_text_from_txt(file_path):
    with open(file_path, "r", encoding="utf-8") as file:
        return file.read()

def extract_text_from_docx(file_path):
    return docx2txt.process(file_path)

def extract_text_from_pdf(file_content):
    # Create a temporary file to write the PDF content to disk
    with tempfile.NamedTemporaryFile(delete=False, suffix='.pdf') as temp_file:
        temp_file.write(file_content.read())
        temp_file_path = temp_file.name

    text = ""
    with fitz.open(temp_file_path) as pdf:
        for page in pdf:
            text += page.get_text()

    # Clean up temporary file after processing
    os.remove(temp_file_path)
    return text

def download_file_from_url(url):
    """Download a file from a URL and return a file-like object."""
    response = requests.get(url)
    response.raise_for_status()  # Will raise an error for a bad status
    return BytesIO(response.content)

# Dictionary for file handlers
FILE_HANDLERS = {
    "text/plain": extract_text_from_txt,
    "application/vnd.openxmlformats-officedocument.wordprocessingml.document": extract_text_from_docx,
    "application/pdf": extract_text_from_pdf,
}

def extract_text_from_file(file_path):
    """Extract text based on file type, whether it's a URL or a local path."""
    # If it's a URL, download the file
    if file_path.startswith('http://') or file_path.startswith('https://'):
        file_content = download_file_from_url(file_path)
        mime_type = "application/pdf"  # Assuming PDF for now, modify as needed for other types
    else:
        mime_type, _ = mimetypes.guess_type(file_path)
        file_content = open(file_path, "rb")

    if mime_type in FILE_HANDLERS:
        try:
            return FILE_HANDLERS[mime_type](file_content).lower()
        except Exception as e:
            print(f"Error reading the file: {e}")
    else:
        print(f"Unsupported file type: {mime_type}")
    return None

def search_keywords_in_text(text, keywords):
    """Search for keywords in the provided text with exact matching."""
    counts = Counter()
    for keyword in keywords:
        # Use regex to match whole words or exact strings
        pattern = rf"\b{re.escape(keyword)}\b"
        counts[keyword] = len(re.findall(pattern, text))
    return counts

def split_text_into_chunks(text, chunk_size=1000):
    """Split the text into chunks of a specified size."""
    return [text[i:i + chunk_size] for i in range(0, len(text), chunk_size)]

def process_chunk(chunk, keywords):
    """Process a single chunk to find keywords."""
    return search_keywords_in_text(chunk, keywords)

def process_file(file_path, keywords):
    """Process a file (either local or from a URL), extract its text, and search for keywords."""
    # Extract text from file
    text = extract_text_from_file(file_path)
    if text:
        # Split the text into chunks
        chunks = split_text_into_chunks(text)

        # Use ThreadPoolExecutor for multithreading
        with ThreadPoolExecutor() as executor:
            results = list(executor.map(lambda chunk: process_chunk(chunk, keywords), chunks))

        # Combine all results
        combined_counts = Counter()
        for result in results:
            combined_counts.update(result)

        return combined_counts
    return Counter()

def main():
    # List of keywords to search for
    keywords = [
        "Java", "Spring", "Spring boot", "Javascript", "TypeScript",
        "SQL", "MySql",
    ]
    keywords = [keyword.lower() for keyword in keywords]

    # Input for file path (can be URL or local path)
    file_path = input("Enter the file URL or path (txt, docx, or pdf): ").strip()

    # Process the file and search for keywords
    found_keywords = process_file(file_path, keywords)

    print("\n**Found Keywords and Counts:**")
    for keyword in keywords:
        count = found_keywords[keyword]
        print(f"- {keyword}: {count}")

if __name__ == "__main__":
    main()
