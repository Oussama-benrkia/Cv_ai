package ai.analys.cvbrk.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;

    public RegisterResponse(int statusCode, String error, String message, String token, String refreshToken) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public RegisterResponse() {
    }

    public static RegisterResponseBuilder builder() {
        return new RegisterResponseBuilder();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public String getToken() {
        return this.token;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RegisterResponse)) return false;
        final RegisterResponse other = (RegisterResponse) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getStatusCode() != other.getStatusCode()) return false;
        final Object this$error = this.getError();
        final Object other$error = other.getError();
        if (this$error == null ? other$error != null : !this$error.equals(other$error)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final Object this$token = this.getToken();
        final Object other$token = other.getToken();
        if (this$token == null ? other$token != null : !this$token.equals(other$token)) return false;
        final Object this$refreshToken = this.getRefreshToken();
        final Object other$refreshToken = other.getRefreshToken();
        if (this$refreshToken == null ? other$refreshToken != null : !this$refreshToken.equals(other$refreshToken))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RegisterResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getStatusCode();
        final Object $error = this.getError();
        result = result * PRIME + ($error == null ? 43 : $error.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final Object $token = this.getToken();
        result = result * PRIME + ($token == null ? 43 : $token.hashCode());
        final Object $refreshToken = this.getRefreshToken();
        result = result * PRIME + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        return result;
    }

    public String toString() {
        return "RegisterResponse(statusCode=" + this.getStatusCode() + ", error=" + this.getError() + ", message=" + this.getMessage() + ", token=" + this.getToken() + ", refreshToken=" + this.getRefreshToken() + ")";
    }

    public static class RegisterResponseBuilder {
        private int statusCode;
        private String error;
        private String message;
        private String token;
        private String refreshToken;

        RegisterResponseBuilder() {
        }

        public RegisterResponseBuilder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public RegisterResponseBuilder error(String error) {
            this.error = error;
            return this;
        }

        public RegisterResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public RegisterResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public RegisterResponseBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public RegisterResponse build() {
            return new RegisterResponse(this.statusCode, this.error, this.message, this.token, this.refreshToken);
        }

        public String toString() {
            return "RegisterResponse.RegisterResponseBuilder(statusCode=" + this.statusCode + ", error=" + this.error + ", message=" + this.message + ", token=" + this.token + ", refreshToken=" + this.refreshToken + ")";
        }
    }
}
