package ai.analys.cvbrk.dto;


import java.time.LocalDateTime;
import java.util.Objects;

public class PostResponse {
    private Long id;
    private String titre;
    private String description;
    private String image;
    private LocalDateTime createdAt;
    public PostResponse() {
    }
    public PostResponse(Long id, String titre, String description, String image, LocalDateTime createdAt) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostResponse that = (PostResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(titre, that.titre) && Objects.equals(description, that.description) && Objects.equals(image, that.image) && Objects.equals(createdAt, that.createdAt);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, titre, description, image, createdAt);
    }
    @Override
    public String toString() {
        return "PostResponse{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}