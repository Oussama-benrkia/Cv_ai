package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;

    @ManyToOne
    @JoinColumn(name = "rh_id", nullable = false)
    private User rh;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Postule> postulants;

    public Post(Long id, String titre, String description, String keyword, String lien, String image, String email, LocalDateTime creatAt, User rh, List<Postule> postulants) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.keyword = keyword;
        this.lien = lien;
        this.image = image;
        this.email = email;
        this.creatAt = creatAt;
        this.rh = rh;
        this.postulants = postulants;
    }

    public Post() {
    }

    public static PostBuilder builder() {
        return new PostBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public String getDescription() {
        return this.description;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getLien() {
        return this.lien;
    }

    public String getImage() {
        return this.image;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDateTime getCreatAt() {
        return this.creatAt;
    }

    public User getRh() {
        return this.rh;
    }

    public List<Postule> getPostulants() {
        return this.postulants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public void setRh(User rh) {
        this.rh = rh;
    }

    public void setPostulants(List<Postule> postulants) {
        this.postulants = postulants;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Post)) return false;
        final Post other = (Post) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$titre = this.getTitre();
        final Object other$titre = other.getTitre();
        if (this$titre == null ? other$titre != null : !this$titre.equals(other$titre)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$keyword = this.getKeyword();
        final Object other$keyword = other.getKeyword();
        if (this$keyword == null ? other$keyword != null : !this$keyword.equals(other$keyword)) return false;
        final Object this$lien = this.getLien();
        final Object other$lien = other.getLien();
        if (this$lien == null ? other$lien != null : !this$lien.equals(other$lien)) return false;
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$creatAt = this.getCreatAt();
        final Object other$creatAt = other.getCreatAt();
        if (this$creatAt == null ? other$creatAt != null : !this$creatAt.equals(other$creatAt)) return false;
        final Object this$rh = this.getRh();
        final Object other$rh = other.getRh();
        if (this$rh == null ? other$rh != null : !this$rh.equals(other$rh)) return false;
        final Object this$postulants = this.getPostulants();
        final Object other$postulants = other.getPostulants();
        if (this$postulants == null ? other$postulants != null : !this$postulants.equals(other$postulants))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Post;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $titre = this.getTitre();
        result = result * PRIME + ($titre == null ? 43 : $titre.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $keyword = this.getKeyword();
        result = result * PRIME + ($keyword == null ? 43 : $keyword.hashCode());
        final Object $lien = this.getLien();
        result = result * PRIME + ($lien == null ? 43 : $lien.hashCode());
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $creatAt = this.getCreatAt();
        result = result * PRIME + ($creatAt == null ? 43 : $creatAt.hashCode());
        final Object $rh = this.getRh();
        result = result * PRIME + ($rh == null ? 43 : $rh.hashCode());
        final Object $postulants = this.getPostulants();
        result = result * PRIME + ($postulants == null ? 43 : $postulants.hashCode());
        return result;
    }

    public String toString() {
        return "Post(id=" + this.getId() + ", titre=" + this.getTitre() + ", description=" + this.getDescription() + ", keyword=" + this.getKeyword() + ", lien=" + this.getLien() + ", image=" + this.getImage() + ", email=" + this.getEmail() + ", creatAt=" + this.getCreatAt() + ", rh=" + this.getRh() + ", postulants=" + this.getPostulants() + ")";
    }

    public static class PostBuilder {
        private Long id;
        private String titre;
        private String description;
        private String keyword;
        private String lien;
        private String image;
        private String email;
        private LocalDateTime creatAt;
        private User rh;
        private List<Postule> postulants;

        PostBuilder() {
        }

        public PostBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PostBuilder titre(String titre) {
            this.titre = titre;
            return this;
        }

        public PostBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PostBuilder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public PostBuilder lien(String lien) {
            this.lien = lien;
            return this;
        }

        public PostBuilder image(String image) {
            this.image = image;
            return this;
        }

        public PostBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PostBuilder creatAt(LocalDateTime creatAt) {
            this.creatAt = creatAt;
            return this;
        }

        public PostBuilder rh(User rh) {
            this.rh = rh;
            return this;
        }

        public PostBuilder postulants(List<Postule> postulants) {
            this.postulants = postulants;
            return this;
        }

        public Post build() {
            return new Post(this.id, this.titre, this.description, this.keyword, this.lien, this.image, this.email, this.creatAt, this.rh, this.postulants);
        }

        public String toString() {
            return "Post.PostBuilder(id=" + this.id + ", titre=" + this.titre + ", description=" + this.description + ", keyword=" + this.keyword + ", lien=" + this.lien + ", image=" + this.image + ", email=" + this.email + ", creatAt=" + this.creatAt + ", rh=" + this.rh + ", postulants=" + this.postulants + ")";
        }
    }
}
