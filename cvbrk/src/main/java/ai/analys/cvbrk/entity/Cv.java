package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private User etudiant;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Postule> postulants;

    public Cv(Long id, String titre, LocalDateTime creatAt, User etudiant, List<Postule> postulants) {
        this.id = id;
        this.titre = titre;
        this.creatAt = creatAt;
        this.etudiant = etudiant;
        this.postulants = postulants;
    }

    public Cv() {
    }

    public static CvBuilder builder() {
        return new CvBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public LocalDateTime getCreatAt() {
        return this.creatAt;
    }

    public User getEtudiant() {
        return this.etudiant;
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

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public void setEtudiant(User etudiant) {
        this.etudiant = etudiant;
    }

    public void setPostulants(List<Postule> postulants) {
        this.postulants = postulants;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Cv)) return false;
        final Cv other = (Cv) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$titre = this.getTitre();
        final Object other$titre = other.getTitre();
        if (this$titre == null ? other$titre != null : !this$titre.equals(other$titre)) return false;
        final Object this$creatAt = this.getCreatAt();
        final Object other$creatAt = other.getCreatAt();
        if (this$creatAt == null ? other$creatAt != null : !this$creatAt.equals(other$creatAt)) return false;
        final Object this$etudiant = this.getEtudiant();
        final Object other$etudiant = other.getEtudiant();
        if (this$etudiant == null ? other$etudiant != null : !this$etudiant.equals(other$etudiant)) return false;
        final Object this$postulants = this.getPostulants();
        final Object other$postulants = other.getPostulants();
        if (this$postulants == null ? other$postulants != null : !this$postulants.equals(other$postulants))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Cv;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $titre = this.getTitre();
        result = result * PRIME + ($titre == null ? 43 : $titre.hashCode());
        final Object $creatAt = this.getCreatAt();
        result = result * PRIME + ($creatAt == null ? 43 : $creatAt.hashCode());
        final Object $etudiant = this.getEtudiant();
        result = result * PRIME + ($etudiant == null ? 43 : $etudiant.hashCode());
        final Object $postulants = this.getPostulants();
        result = result * PRIME + ($postulants == null ? 43 : $postulants.hashCode());
        return result;
    }

    public String toString() {
        return "Cv(id=" + this.getId() + ", titre=" + this.getTitre() + ", creatAt=" + this.getCreatAt() + ", etudiant=" + this.getEtudiant() + ", postulants=" + this.getPostulants() + ")";
    }

    public static class CvBuilder {
        private Long id;
        private String titre;
        private LocalDateTime creatAt;
        private User etudiant;
        private List<Postule> postulants;

        CvBuilder() {
        }

        public CvBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CvBuilder titre(String titre) {
            this.titre = titre;
            return this;
        }

        public CvBuilder creatAt(LocalDateTime creatAt) {
            this.creatAt = creatAt;
            return this;
        }

        public CvBuilder etudiant(User etudiant) {
            this.etudiant = etudiant;
            return this;
        }

        public CvBuilder postulants(List<Postule> postulants) {
            this.postulants = postulants;
            return this;
        }

        public Cv build() {
            return new Cv(this.id, this.titre, this.creatAt, this.etudiant, this.postulants);
        }

        public String toString() {
            return "Cv.CvBuilder(id=" + this.id + ", titre=" + this.titre + ", creatAt=" + this.creatAt + ", etudiant=" + this.etudiant + ", postulants=" + this.postulants + ")";
        }
    }
}
