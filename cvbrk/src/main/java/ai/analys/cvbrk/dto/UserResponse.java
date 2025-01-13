package ai.analys.cvbrk.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserResponse {
    private Long id;
    private String nom;
    private String email;
    private String image;
    private LocalDateTime creatAt;
    public UserResponse() {
    }
    public UserResponse(Long id, String nom, String email, String image, LocalDateTime creatAt) {
        this.id = id;
        this.nom = nom;
        this.email = email;
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
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(nom, that.nom) && Objects.equals(email, that.email) && Objects.equals(image, that.image) && Objects.equals(creatAt, that.creatAt);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nom, email, image, creatAt);
    }
    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", creatAt=" + creatAt +
                '}';
    }
}