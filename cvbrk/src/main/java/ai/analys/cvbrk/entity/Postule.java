package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Postule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double pourcentage;
    private String description;
    private String message;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Cv etudiant;

    public Postule(Long id, double pourcentage, String description, String message, LocalDateTime creatAt, Post post, Cv etudiant) {
        this.id = id;
        this.pourcentage = pourcentage;
        this.description = description;
        this.message = message;
        this.creatAt = creatAt;
        this.post = post;
        this.etudiant = etudiant;
    }

    public Postule() {
    }

    public static PostuleBuilder builder() {
        return new PostuleBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public double getPourcentage() {
        return this.pourcentage;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMessage() {
        return this.message;
    }

    public LocalDateTime getCreatAt() {
        return this.creatAt;
    }

    public Post getPost() {
        return this.post;
    }

    public Cv getEtudiant() {
        return this.etudiant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setEtudiant(Cv etudiant) {
        this.etudiant = etudiant;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Postule)) return false;
        final Postule other = (Postule) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        if (Double.compare(this.getPourcentage(), other.getPourcentage()) != 0) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final Object this$creatAt = this.getCreatAt();
        final Object other$creatAt = other.getCreatAt();
        if (this$creatAt == null ? other$creatAt != null : !this$creatAt.equals(other$creatAt)) return false;
        final Object this$post = this.getPost();
        final Object other$post = other.getPost();
        if (this$post == null ? other$post != null : !this$post.equals(other$post)) return false;
        final Object this$etudiant = this.getEtudiant();
        final Object other$etudiant = other.getEtudiant();
        if (this$etudiant == null ? other$etudiant != null : !this$etudiant.equals(other$etudiant)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Postule;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final long $pourcentage = Double.doubleToLongBits(this.getPourcentage());
        result = result * PRIME + (int) ($pourcentage >>> 32 ^ $pourcentage);
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final Object $creatAt = this.getCreatAt();
        result = result * PRIME + ($creatAt == null ? 43 : $creatAt.hashCode());
        final Object $post = this.getPost();
        result = result * PRIME + ($post == null ? 43 : $post.hashCode());
        final Object $etudiant = this.getEtudiant();
        result = result * PRIME + ($etudiant == null ? 43 : $etudiant.hashCode());
        return result;
    }

    public String toString() {
        return "Postule(id=" + this.getId() + ", pourcentage=" + this.getPourcentage() + ", description=" + this.getDescription() + ", message=" + this.getMessage() + ", creatAt=" + this.getCreatAt() + ", post=" + this.getPost() + ", etudiant=" + this.getEtudiant() + ")";
    }

    public static class PostuleBuilder {
        private Long id;
        private double pourcentage;
        private String description;
        private String message;
        private LocalDateTime creatAt;
        private Post post;
        private Cv etudiant;

        PostuleBuilder() {
        }

        public PostuleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PostuleBuilder pourcentage(double pourcentage) {
            this.pourcentage = pourcentage;
            return this;
        }

        public PostuleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PostuleBuilder message(String message) {
            this.message = message;
            return this;
        }

        public PostuleBuilder creatAt(LocalDateTime creatAt) {
            this.creatAt = creatAt;
            return this;
        }

        public PostuleBuilder post(Post post) {
            this.post = post;
            return this;
        }

        public PostuleBuilder etudiant(Cv etudiant) {
            this.etudiant = etudiant;
            return this;
        }

        public Postule build() {
            return new Postule(this.id, this.pourcentage, this.description, this.message, this.creatAt, this.post, this.etudiant);
        }

        public String toString() {
            return "Postule.PostuleBuilder(id=" + this.id + ", pourcentage=" + this.pourcentage + ", description=" + this.description + ", message=" + this.message + ", creatAt=" + this.creatAt + ", post=" + this.post + ", etudiant=" + this.etudiant + ")";
        }
    }
}