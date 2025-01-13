package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String email;
    private String password;
    private String image;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;
    public User() {
    }
    public User(Long id, String nom, String email, String password, String image, LocalDateTime creatAt) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.image = image;
        this.creatAt = creatAt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
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
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nom, user.nom) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(image, user.image) && Objects.equals(creatAt, user.creatAt);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nom, email, password, image, creatAt);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", creatAt=" + creatAt +
                '}';
    }
}