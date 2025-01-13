package ai.analys.cvbrk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ai.analys.cvbrk.validation.OnCreate;


import java.util.Objects;


public class PostRequest {
        @NotBlank(groups = OnCreate.class, message = "Titre must not be blank")
        private String titre;
        @NotBlank(groups = OnCreate.class, message = "Description must not be blank")
        private String description;
        private String image;
        @NotNull(groups = OnCreate.class, message = "Rh ID must not be null")
        private Long rhId;

        public PostRequest() {
        }

        public PostRequest(String titre, String description, String image, Long rhId) {
                this.titre = titre;
                this.description = description;
                this.image = image;
                this.rhId = rhId;
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

        public Long getRhId() {
                return rhId;
        }

        public void setRhId(Long rhId) {
                this.rhId = rhId;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PostRequest that = (PostRequest) o;
                return Objects.equals(titre, that.titre) && Objects.equals(description, that.description) && Objects.equals(image, that.image) && Objects.equals(rhId, that.rhId);
        }

        @Override
        public int hashCode() {
                return Objects.hash(titre, description, image, rhId);
        }

        @Override
        public String toString() {
                return "PostRequest{" +
                        "titre='" + titre + '\'' +
                        ", description='" + description + '\'' +
                        ", image='" + image + '\'' +
                        ", rhId=" + rhId +
                        '}';
        }
}