package ai.analys.cvbrk.dto;

public class CvResponse {
    Long id;
    String titre;
    String path;
    String creat_at;

    public CvResponse(Long id, String titre, String path, String creat_at) {
        this.id = id;
        this.titre = titre;
        this.path = path;
        this.creat_at = creat_at;
    }

    public CvResponse() {
    }

    public static CvResponseBuilder builder() {
        return new CvResponseBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public String getPath() {
        return this.path;
    }

    public String getCreat_at() {
        return this.creat_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCreat_at(String creat_at) {
        this.creat_at = creat_at;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CvResponse)) return false;
        final CvResponse other = (CvResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$titre = this.getTitre();
        final Object other$titre = other.getTitre();
        if (this$titre == null ? other$titre != null : !this$titre.equals(other$titre)) return false;
        final Object this$path = this.getPath();
        final Object other$path = other.getPath();
        if (this$path == null ? other$path != null : !this$path.equals(other$path)) return false;
        final Object this$creat_at = this.getCreat_at();
        final Object other$creat_at = other.getCreat_at();
        if (this$creat_at == null ? other$creat_at != null : !this$creat_at.equals(other$creat_at)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CvResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $titre = this.getTitre();
        result = result * PRIME + ($titre == null ? 43 : $titre.hashCode());
        final Object $path = this.getPath();
        result = result * PRIME + ($path == null ? 43 : $path.hashCode());
        final Object $creat_at = this.getCreat_at();
        result = result * PRIME + ($creat_at == null ? 43 : $creat_at.hashCode());
        return result;
    }

    public String toString() {
        return "CvResponse(id=" + this.getId() + ", titre=" + this.getTitre() + ", path=" + this.getPath() + ", creat_at=" + this.getCreat_at() + ")";
    }

    public static class CvResponseBuilder {
        private Long id;
        private String titre;
        private String path;
        private String creat_at;

        CvResponseBuilder() {
        }

        public CvResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CvResponseBuilder titre(String titre) {
            this.titre = titre;
            return this;
        }

        public CvResponseBuilder path(String path) {
            this.path = path;
            return this;
        }

        public CvResponseBuilder creat_at(String creat_at) {
            this.creat_at = creat_at;
            return this;
        }

        public CvResponse build() {
            return new CvResponse(this.id, this.titre, this.path, this.creat_at);
        }

        public String toString() {
            return "CvResponse.CvResponseBuilder(id=" + this.id + ", titre=" + this.titre + ", path=" + this.path + ", creat_at=" + this.creat_at + ")";
        }
    }
}
