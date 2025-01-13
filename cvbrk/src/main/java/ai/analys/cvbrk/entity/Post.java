package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String keyword;
    private String lien;
    private String image;
    private String email;
    @ManyToOne
    @JoinColumn(name = "rh_id", nullable = false)
    private Rh rh;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Postule> postulants;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;
    public Post() {
    }
    public Post(Long id, String titre, String description, String keyword, String lien, String image, String email, Rh rh, List<Postule> postulants, LocalDateTime creatAt) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.keyword = keyword;
        this.lien = lien;
        this.image = image;
        this.email = email;
        this.rh = rh;
        this.postulants = postulants;
        this.creatAt = creatAt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getLien() {
        return lien;
    }
    public void setLien(String lien) {
        this.lien = lien;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Rh getRh() {
        return rh;
    }
    public void setRh(Rh rh) {
        this.rh = rh;
    }
    public List<Postule> getPostulants() {
        return postulants;
    }
    public void setPostulants(List<Postule> postulants) {
        this.postulants = postulants;
    }
    public LocalDateTime getCreatAt() {
        return creatAt;
    }
    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(titre, post.titre) && Objects.equals(description, post.description) && Objects.equals(keyword, post.keyword) && Objects.equals(lien, post.lien) && Objects.equals(image, post.image) && Objects.equals(email, post.email) && Objects.equals(rh, post.rh) && Objects.equals(postulants, post.postulants) && Objects.equals(creatAt, post.creatAt);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, titre, description, keyword, lien, image, email, rh, postulants, creatAt);
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", keyword='" + keyword + '\'' +
                ", lien='" + lien + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", rh=" + rh +
                ", postulants=" + postulants +
                ", creatAt=" + creatAt +
                '}';
    }
}