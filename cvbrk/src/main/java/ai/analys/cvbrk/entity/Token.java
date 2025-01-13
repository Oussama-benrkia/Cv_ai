package ai.analys.cvbrk.entity;

import jakarta.persistence.*;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    private boolean expired;
    private boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isRefreshToken;
    private String refreshToken;

    public Token(Long id, String token, boolean expired, boolean revoked, User user, boolean isRefreshToken, String refreshToken) {
        this.id = id;
        this.token = token;
        this.expired = expired;
        this.revoked = revoked;
        this.user = user;
        this.isRefreshToken = isRefreshToken;
        this.refreshToken = refreshToken;
    }

    public Token() {
    }

    public static TokenBuilder builder() {
        return new TokenBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getToken() {
        return this.token;
    }

    public boolean isExpired() {
        return this.expired;
    }

    public boolean isRevoked() {
        return this.revoked;
    }

    public User getUser() {
        return this.user;
    }

    public boolean isRefreshToken() {
        return this.isRefreshToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRefreshToken(boolean isRefreshToken) {
        this.isRefreshToken = isRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Token)) return false;
        final Token other = (Token) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$token = this.getToken();
        final Object other$token = other.getToken();
        if (this$token == null ? other$token != null : !this$token.equals(other$token)) return false;
        if (this.isExpired() != other.isExpired()) return false;
        if (this.isRevoked() != other.isRevoked()) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        if (this.isRefreshToken() != other.isRefreshToken()) return false;
        final Object this$refreshToken = this.getRefreshToken();
        final Object other$refreshToken = other.getRefreshToken();
        if (this$refreshToken == null ? other$refreshToken != null : !this$refreshToken.equals(other$refreshToken))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Token;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $token = this.getToken();
        result = result * PRIME + ($token == null ? 43 : $token.hashCode());
        result = result * PRIME + (this.isExpired() ? 79 : 97);
        result = result * PRIME + (this.isRevoked() ? 79 : 97);
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        result = result * PRIME + (this.isRefreshToken() ? 79 : 97);
        final Object $refreshToken = this.getRefreshToken();
        result = result * PRIME + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        return result;
    }

    public String toString() {
        return "Token(id=" + this.getId() + ", token=" + this.getToken() + ", expired=" + this.isExpired() + ", revoked=" + this.isRevoked() + ", user=" + this.getUser() + ", isRefreshToken=" + this.isRefreshToken() + ", refreshToken=" + this.getRefreshToken() + ")";
    }

    public static class TokenBuilder {
        private Long id;
        private String token;
        private boolean expired;
        private boolean revoked;
        private User user;
        private boolean isRefreshToken;
        private String refreshToken;

        TokenBuilder() {
        }

        public TokenBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TokenBuilder token(String token) {
            this.token = token;
            return this;
        }

        public TokenBuilder expired(boolean expired) {
            this.expired = expired;
            return this;
        }

        public TokenBuilder revoked(boolean revoked) {
            this.revoked = revoked;
            return this;
        }

        public TokenBuilder user(User user) {
            this.user = user;
            return this;
        }

        public TokenBuilder isRefreshToken(boolean isRefreshToken) {
            this.isRefreshToken = isRefreshToken;
            return this;
        }

        public TokenBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Token build() {
            return new Token(this.id, this.token, this.expired, this.revoked, this.user, this.isRefreshToken, this.refreshToken);
        }

        public String toString() {
            return "Token.TokenBuilder(id=" + this.id + ", token=" + this.token + ", expired=" + this.expired + ", revoked=" + this.revoked + ", user=" + this.user + ", isRefreshToken=" + this.isRefreshToken + ", refreshToken=" + this.refreshToken + ")";
        }
    }
}
