package ai.analys.cvbrk.dto;

import java.time.LocalDateTime;

public class PostuleResponse {
    private Long id;
    private Long postId;
    private String description;
    private double pourcentage;
    private LocalDateTime creatAt;
    private Long etudiantId;

    public PostuleResponse() {
    }

    public PostuleResponse(Long id, Long postId, String description, double pourcentage, LocalDateTime creatAt, Long etudiantId) {
        this.id = id;
        this.postId = postId;
        this.description = description;
        this.pourcentage = pourcentage;
        this.creatAt = creatAt;
        this.etudiantId = etudiantId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public LocalDateTime getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }
}