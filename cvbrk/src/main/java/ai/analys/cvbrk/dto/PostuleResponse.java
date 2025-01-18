package ai.analys.cvbrk.dto;

import java.time.LocalDateTime;

public class PostuleResponse {
    private Long id;
    private Long postId;
    private String description;
    private String message;
    private double pourcentage;
    private String creatAt;
    private Long etudiantId;

    public PostuleResponse(Long id, Long postId, String description, String message, double pourcentage, String creatAt, Long etudiantId) {
        this.id = id;
        this.postId = postId;
        this.description = description;
        this.message = message;
        this.pourcentage = pourcentage;
        this.creatAt = creatAt;
        this.etudiantId = etudiantId;
    }

    public PostuleResponse() {
    }

    public static PostuleResponseBuilder builder() {
        return new PostuleResponseBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getPostId() {
        return this.postId;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMessage() {
        return this.message;
    }

    public double getPourcentage() {
        return this.pourcentage;
    }

    public String getCreatAt() {
        return this.creatAt;
    }

    public Long getEtudiantId() {
        return this.etudiantId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PostuleResponse)) return false;
        final PostuleResponse other = (PostuleResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$postId = this.getPostId();
        final Object other$postId = other.getPostId();
        if (this$postId == null ? other$postId != null : !this$postId.equals(other$postId)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        if (Double.compare(this.getPourcentage(), other.getPourcentage()) != 0) return false;
        final Object this$creatAt = this.getCreatAt();
        final Object other$creatAt = other.getCreatAt();
        if (this$creatAt == null ? other$creatAt != null : !this$creatAt.equals(other$creatAt)) return false;
        final Object this$etudiantId = this.getEtudiantId();
        final Object other$etudiantId = other.getEtudiantId();
        if (this$etudiantId == null ? other$etudiantId != null : !this$etudiantId.equals(other$etudiantId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PostuleResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $postId = this.getPostId();
        result = result * PRIME + ($postId == null ? 43 : $postId.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final long $pourcentage = Double.doubleToLongBits(this.getPourcentage());
        result = result * PRIME + (int) ($pourcentage >>> 32 ^ $pourcentage);
        final Object $creatAt = this.getCreatAt();
        result = result * PRIME + ($creatAt == null ? 43 : $creatAt.hashCode());
        final Object $etudiantId = this.getEtudiantId();
        result = result * PRIME + ($etudiantId == null ? 43 : $etudiantId.hashCode());
        return result;
    }

    public String toString() {
        return "PostuleResponse(id=" + this.getId() + ", postId=" + this.getPostId() + ", description=" + this.getDescription() + ", message=" + this.getMessage() + ", pourcentage=" + this.getPourcentage() + ", creatAt=" + this.getCreatAt() + ", etudiantId=" + this.getEtudiantId() + ")";
    }

    public static class PostuleResponseBuilder {
        private Long id;
        private Long postId;
        private String description;
        private String message;
        private double pourcentage;
        private String creatAt;
        private Long etudiantId;

        PostuleResponseBuilder() {
        }

        public PostuleResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PostuleResponseBuilder postId(Long postId) {
            this.postId = postId;
            return this;
        }

        public PostuleResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PostuleResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public PostuleResponseBuilder pourcentage(double pourcentage) {
            this.pourcentage = pourcentage;
            return this;
        }

        public PostuleResponseBuilder creatAt(String creatAt) {
            this.creatAt = creatAt;
            return this;
        }

        public PostuleResponseBuilder etudiantId(Long etudiantId) {
            this.etudiantId = etudiantId;
            return this;
        }

        public PostuleResponse build() {
            return new PostuleResponse(this.id, this.postId, this.description, this.message, this.pourcentage, this.creatAt, this.etudiantId);
        }

        public String toString() {
            return "PostuleResponse.PostuleResponseBuilder(id=" + this.id + ", postId=" + this.postId + ", description=" + this.description + ", message=" + this.message + ", pourcentage=" + this.pourcentage + ", creatAt=" + this.creatAt + ", etudiantId=" + this.etudiantId + ")";
        }
    }
}