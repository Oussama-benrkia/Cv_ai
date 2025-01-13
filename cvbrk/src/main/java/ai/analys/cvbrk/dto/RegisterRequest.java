package ai.analys.cvbrk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class RegisterRequest {

    public static final class RegisterRequestLogin {
        @NotBlank(message = "Email must not be blank")
        @Email(message = "Email must be a valid email address")
        private final
        String email;

        @NotBlank(message = "Password must not be blank")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        private final
        String password;

        RegisterRequestLogin(@NotBlank(message = "Email must not be blank") @Email(message = "Email must be a valid email address") String email, @NotBlank(message = "Password must not be blank") @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
            this.email = email;
            this.password = password;
        }

        public static RegisterRequestLoginBuilder builder() {
            return new RegisterRequestLoginBuilder();
        }

        public @NotBlank(message = "Email must not be blank") @Email(message = "Email must be a valid email address") String getEmail() {
            return this.email;
        }

        public @NotBlank(message = "Password must not be blank") @Size(min = 8, message = "Password must be at least 8 characters long") String getPassword() {
            return this.password;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof RegisterRequestLogin)) return false;
            final RegisterRequestLogin other = (RegisterRequestLogin) o;
            final Object this$email = this.getEmail();
            final Object other$email = other.getEmail();
            if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
            final Object this$password = this.getPassword();
            final Object other$password = other.getPassword();
            if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
            return true;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $email = this.getEmail();
            result = result * PRIME + ($email == null ? 43 : $email.hashCode());
            final Object $password = this.getPassword();
            result = result * PRIME + ($password == null ? 43 : $password.hashCode());
            return result;
        }

        public String toString() {
            return "RegisterRequest.RegisterRequestLogin(email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
        }

        public static class RegisterRequestLoginBuilder {
            private @NotBlank(message = "Email must not be blank")
            @Email(message = "Email must be a valid email address") String email;
            private @NotBlank(message = "Password must not be blank")
            @Size(min = 8, message = "Password must be at least 8 characters long") String password;

            RegisterRequestLoginBuilder() {
            }

            public RegisterRequestLoginBuilder email(@NotBlank(message = "Email must not be blank") @Email(message = "Email must be a valid email address") String email) {
                this.email = email;
                return this;
            }

            public RegisterRequestLoginBuilder password(@NotBlank(message = "Password must not be blank") @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
                this.password = password;
                return this;
            }

            public RegisterRequestLogin build() {
                return new RegisterRequestLogin(this.email, this.password);
            }

            public String toString() {
                return "RegisterRequest.RegisterRequestLogin.RegisterRequestLoginBuilder(email=" + this.email + ", password=" + this.password + ")";
            }
        }
    }

    public static final class RegisterRequestRegister {
        @NotBlank(message = "Prenom must not be blank")
        private final
        String prenom;

        @NotBlank(message = "Nom must not be blank")
        private final
        String nom;

        @NotBlank(message = "Email must not be blank")
        @Email(message = "Email must be a valid email address")
        private final
        String email;

        @NotBlank(message = "Password must not be blank")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        private final
        String password;

        private final String role;

        private final MultipartFile file;

        RegisterRequestRegister(@NotBlank(message = "Prenom must not be blank") String prenom, @NotBlank(message = "Nom must not be blank") String nom, @NotBlank(message = "Email must not be blank") @Email(message = "Email must be a valid email address") String email, @NotBlank(message = "Password must not be blank") @Size(min = 8, message = "Password must be at least 8 characters long") String password, String role, MultipartFile file) {
            this.prenom = prenom;
            this.nom = nom;
            this.email = email;
            this.password = password;
            this.role = role;
            this.file = file;
        }

        public static RegisterRequestRegisterBuilder builder() {
            return new RegisterRequestRegisterBuilder();
        }

        public @NotBlank(message = "Prenom must not be blank") String getPrenom() {
            return this.prenom;
        }

        public @NotBlank(message = "Nom must not be blank") String getNom() {
            return this.nom;
        }

        public @NotBlank(message = "Email must not be blank") @Email(message = "Email must be a valid email address") String getEmail() {
            return this.email;
        }

        public @NotBlank(message = "Password must not be blank") @Size(min = 8, message = "Password must be at least 8 characters long") String getPassword() {
            return this.password;
        }

        public String getRole() {
            return this.role;
        }

        public MultipartFile getFile() {
            return this.file;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof RegisterRequestRegister)) return false;
            final RegisterRequestRegister other = (RegisterRequestRegister) o;
            final Object this$prenom = this.getPrenom();
            final Object other$prenom = other.getPrenom();
            if (this$prenom == null ? other$prenom != null : !this$prenom.equals(other$prenom)) return false;
            final Object this$nom = this.getNom();
            final Object other$nom = other.getNom();
            if (this$nom == null ? other$nom != null : !this$nom.equals(other$nom)) return false;
            final Object this$email = this.getEmail();
            final Object other$email = other.getEmail();
            if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
            final Object this$password = this.getPassword();
            final Object other$password = other.getPassword();
            if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
            final Object this$role = this.getRole();
            final Object other$role = other.getRole();
            if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
            final Object this$file = this.getFile();
            final Object other$file = other.getFile();
            if (this$file == null ? other$file != null : !this$file.equals(other$file)) return false;
            return true;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $prenom = this.getPrenom();
            result = result * PRIME + ($prenom == null ? 43 : $prenom.hashCode());
            final Object $nom = this.getNom();
            result = result * PRIME + ($nom == null ? 43 : $nom.hashCode());
            final Object $email = this.getEmail();
            result = result * PRIME + ($email == null ? 43 : $email.hashCode());
            final Object $password = this.getPassword();
            result = result * PRIME + ($password == null ? 43 : $password.hashCode());
            final Object $role = this.getRole();
            result = result * PRIME + ($role == null ? 43 : $role.hashCode());
            final Object $file = this.getFile();
            result = result * PRIME + ($file == null ? 43 : $file.hashCode());
            return result;
        }

        public String toString() {
            return "RegisterRequest.RegisterRequestRegister(prenom=" + this.getPrenom() + ", nom=" + this.getNom() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", role=" + this.getRole() + ", file=" + this.getFile() + ")";
        }

        public static class RegisterRequestRegisterBuilder {
            private @NotBlank(message = "Prenom must not be blank") String prenom;
            private @NotBlank(message = "Nom must not be blank") String nom;
            private @NotBlank(message = "Email must not be blank")
            @Email(message = "Email must be a valid email address") String email;
            private @NotBlank(message = "Password must not be blank")
            @Size(min = 8, message = "Password must be at least 8 characters long") String password;
            private String role;
            private MultipartFile file;

            RegisterRequestRegisterBuilder() {
            }

            public RegisterRequestRegisterBuilder prenom(@NotBlank(message = "Prenom must not be blank") String prenom) {
                this.prenom = prenom;
                return this;
            }

            public RegisterRequestRegisterBuilder nom(@NotBlank(message = "Nom must not be blank") String nom) {
                this.nom = nom;
                return this;
            }

            public RegisterRequestRegisterBuilder email(@NotBlank(message = "Email must not be blank") @Email(message = "Email must be a valid email address") String email) {
                this.email = email;
                return this;
            }

            public RegisterRequestRegisterBuilder password(@NotBlank(message = "Password must not be blank") @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
                this.password = password;
                return this;
            }

            public RegisterRequestRegisterBuilder role(String role) {
                this.role = role;
                return this;
            }

            public RegisterRequestRegisterBuilder file(MultipartFile file) {
                this.file = file;
                return this;
            }

            public RegisterRequestRegister build() {
                return new RegisterRequestRegister(this.prenom, this.nom, this.email, this.password, this.role, this.file);
            }

            public String toString() {
                return "RegisterRequest.RegisterRequestRegister.RegisterRequestRegisterBuilder(prenom=" + this.prenom + ", nom=" + this.nom + ", email=" + this.email + ", password=" + this.password + ", role=" + this.role + ", file=" + this.file + ")";
            }
        }
    }

    public static final class RegisterToken {
        @NotBlank(message = "Token must not be blank")
        private final
        String token;

        RegisterToken(@NotBlank(message = "Token must not be blank") String token) {
            this.token = token;
        }

        public static RegisterTokenBuilder builder() {
            return new RegisterTokenBuilder();
        }

        public @NotBlank(message = "Token must not be blank") String getToken() {
            return this.token;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof RegisterToken)) return false;
            final RegisterToken other = (RegisterToken) o;
            final Object this$token = this.getToken();
            final Object other$token = other.getToken();
            if (this$token == null ? other$token != null : !this$token.equals(other$token)) return false;
            return true;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $token = this.getToken();
            result = result * PRIME + ($token == null ? 43 : $token.hashCode());
            return result;
        }

        public String toString() {
            return "RegisterRequest.RegisterToken(token=" + this.getToken() + ")";
        }

        public static class RegisterTokenBuilder {
            private @NotBlank(message = "Token must not be blank") String token;

            RegisterTokenBuilder() {
            }

            public RegisterTokenBuilder token(@NotBlank(message = "Token must not be blank") String token) {
                this.token = token;
                return this;
            }

            public RegisterToken build() {
                return new RegisterToken(this.token);
            }

            public String toString() {
                return "RegisterRequest.RegisterToken.RegisterTokenBuilder(token=" + this.token + ")";
            }
        }
    }
}
