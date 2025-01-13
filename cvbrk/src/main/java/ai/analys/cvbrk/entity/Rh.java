package ai.analys.cvbrk.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Rh extends User {

    @OneToMany(mappedBy = "rh", cascade = CascadeType.ALL)
    private List<Post> posts;

    public Rh() {
    }

    public Rh(List<Post> posts) {
        this.posts = posts;
    }


    public Rh(Long id, String nom, String email, String password, String image, LocalDateTime creatAt, List<Post> posts) {
        super(id, nom, email, password, image, creatAt);
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rh rh = (Rh) o;
        return Objects.equals(posts, rh.posts);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), posts);
    }
    @Override
    public String toString() {
        return "Rh{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", posts=" + posts +
                '}';
    }
}