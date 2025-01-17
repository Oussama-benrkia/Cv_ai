# postule.py
from dataclasses import dataclass

@dataclass
class Postule:
    id: int
    postId: int
    description: str
    message: str
    pourcentage: int
    creatAt: str
    etudiantId: int

# Function to convert API response to DTO object
def parse_postule(data):
    return Postule(
        id=data["id"],
        postId=data["postId"],
        description=data.get("description"),
        message=data.get("message"),
        pourcentage=data["pourcentage"],
        creatAt=data["creatAt"],
        etudiantId=data["etudiantId"]
    )
