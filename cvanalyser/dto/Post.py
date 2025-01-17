# dto/Post.py
from dataclasses import dataclass
from typing import List

@dataclass
class Post:
    id: int
    titre: str
    description: str
    image: str | None  # Making image optional by allowing None
    createdAt: str
    keyword: List[str]
    email: str
    lien: str

def parse_post(data):
    return Post(
        id=data["id"],
        titre=data["titre"],
        description=data["description"],
        image=data.get("image", None),  # Use get() with None as default value
        createdAt=data["createdAt"],
        keyword=data["keyword"],
        email=data["email"],
        lien=data["lien"]
    )