from flask import Flask

# Create a Flask application instance
app = Flask(__name__)

# Define a route for the root URL
@app.route('/')
def hello_world():
    response = requests.get(url)
    response.raise_for_status()  # Will raise an error for a bad status
    return BytesIO(response.content)
# Run the application
if __name__ == '__main__':
    app.run(debug=True)
