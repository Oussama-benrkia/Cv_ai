package ai.analys.cvbrk.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    private Long id;
    private String titre;
    private String description;
    private String image;
    private String createdAt;
    private List<String> keyword;
    private String Lien;
    private String email;


    public PostResponse(Long id, String titre, String description, String image, String createdAt, List<String> keyword, String Lien, String email) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
        this.keyword = keyword;
        this.Lien = Lien;
        this.email = email;
    }

    public PostResponse() {
    }

    public static PostResponseBuilder builder() {
        return new PostResponseBuilder();
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

    public String getImage() {
        return this.image;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public List<String> getKeyword() {
        return this.keyword;
    }

    public String getLien() {
        return this.Lien;
    }

    public String getEmail() {
        return this.email;
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public void setLien(String Lien) {
        this.Lien = Lien;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PostResponse)) return false;
        final PostResponse other = (PostResponse) o;
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
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        final Object this$createdAt = this.getCreatedAt();
        final Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final Object this$keyword = this.getKeyword();
        final Object other$keyword = other.getKeyword();
        if (this$keyword == null ? other$keyword != null : !this$keyword.equals(other$keyword)) return false;
        final Object this$Lien = this.getLien();
        final Object other$Lien = other.getLien();
        if (this$Lien == null ? other$Lien != null : !this$Lien.equals(other$Lien)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PostResponse;
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
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        final Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final Object $keyword = this.getKeyword();
        result = result * PRIME + ($keyword == null ? 43 : $keyword.hashCode());
        final Object $Lien = this.getLien();
        result = result * PRIME + ($Lien == null ? 43 : $Lien.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "PostResponse(id=" + this.getId() + ", titre=" + this.getTitre() + ", description=" + this.getDescription() + ", image=" + this.getImage() + ", createdAt=" + this.getCreatedAt() + ", keyword=" + this.getKeyword() + ", Lien=" + this.getLien() + ", email=" + this.getEmail() + ")";
    }

    public static class PostResponseBuilder {
        private Long id;
        private String titre;
        private String description;
        private String image;
        private String createdAt;
        private List<String> keyword;
        private String Lien;
        private String email;

        PostResponseBuilder() {
        }

        public PostResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PostResponseBuilder titre(String titre) {
            this.titre = titre;
            return this;
        }

        public PostResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public PostResponseBuilder image(String image) {
            this.image = image;
            return this;
        }

        public PostResponseBuilder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PostResponseBuilder keyword(List<String> keyword) {
            this.keyword = keyword;
            return this;
        }

        public PostResponseBuilder Lien(String Lien) {
            this.Lien = Lien;
            return this;
        }

        public PostResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PostResponse build() {
            return new PostResponse(this.id, this.titre, this.description, this.image, this.createdAt, this.keyword, this.Lien, this.email);
        }

        public String toString() {
            return "PostResponse.PostResponseBuilder(id=" + this.id + ", titre=" + this.titre + ", description=" + this.description + ", image=" + this.image + ", createdAt=" + this.createdAt + ", keyword=" + this.keyword + ", Lien=" + this.Lien + ", email=" + this.email + ")";
        }
    }
}