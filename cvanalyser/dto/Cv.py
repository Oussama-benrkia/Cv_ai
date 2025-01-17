# dto/Cv.py
from dataclasses import dataclass

@dataclass
class Cv:
    id: int
    titre: str
    path: str
    creat_at: str

def parse_cv(data):
    return Cv(
        id=data["id"],
        titre=data["titre"],
        path=data["path"],
        creat_at=data["creat_at"]
    )