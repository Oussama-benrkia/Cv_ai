from flask import Flask, jsonify
from services import fetch_and_process_data


# Create a Flask application instance
app = Flask(__name__)

# Define the API endpoint URLs


# Define a route for the root URL
@app.route('/api/ana/cv/<int:id>')
def hello_world(id):
    result = fetch_and_process_data(id)  # Use the dynamic ID in the function
    if isinstance(result, tuple):  # Handle error case
        return jsonify(result[0]), result[1]
    return jsonify(result)

# Run the application
if __name__ == '__main__':
    
    app.run(debug=True)
