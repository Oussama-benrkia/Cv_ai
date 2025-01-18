package ai.analys.cvbrk.analyse;

import java.util.Map;

public class DataResponse {
    private int post_id;
    private Map<String, Long> data;

    public DataResponse(int post_id, Map<String, Long> data) {
        this.post_id = post_id;
        this.data = data;
    }

    public DataResponse() {
    }

    public static DataResponseBuilder builder() {
        return new DataResponseBuilder();
    }

    public int getPost_id() {
        return this.post_id;
    }

    public Map<String, Long> getData() {
        return this.data;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setData(Map<String, Long> data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DataResponse)) return false;
        final DataResponse other = (DataResponse) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getPost_id() != other.getPost_id()) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DataResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getPost_id();
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "DataResponse(post_id=" + this.getPost_id() + ", data=" + this.getData() + ")";
    }

    public static class DataResponseBuilder {
        private int post_id;
        private Map<String, Long> data;

        DataResponseBuilder() {
        }

        public DataResponseBuilder post_id(int post_id) {
            this.post_id = post_id;
            return this;
        }

        public DataResponseBuilder data(Map<String, Long> data) {
            this.data = data;
            return this;
        }

        public DataResponse build() {
            return new DataResponse(this.post_id, this.data);
        }

        public String toString() {
            return "DataResponse.DataResponseBuilder(post_id=" + this.post_id + ", data=" + this.data + ")";
        }
    }
}
