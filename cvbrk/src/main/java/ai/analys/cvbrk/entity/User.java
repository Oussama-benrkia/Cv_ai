package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String image;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cv> cvs;

    @OneToMany(mappedBy = "rh", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;


    public User(Long id, String nom, String prenom, String email, String password, String image, Role role, LocalDateTime creatAt, List<Cv> cvs, List<Post> posts) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.creatAt = creatAt;
        this.cvs = cvs;
        this.posts = posts;
    }

    public User() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority( role.name()));
    }

    @Override
    public String getUsername() {
        return email; // Return email as username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getImage() {
        return this.image;
    }

    public Role getRole() {
        return this.role;
    }

    public LocalDateTime getCreatAt() {
        return this.creatAt;
    }

    public List<Cv> getCvs() {
        return this.cvs;
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }

    public void setCvs(List<Cv> cvs) {
        this.cvs = cvs;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nom = this.getNom();
        final Object other$nom = other.getNom();
        if (this$nom == null ? other$nom != null : !this$nom.equals(other$nom)) return false;
        final Object this$prenom = this.getPrenom();
        final Object other$prenom = other.getPrenom();
        if (this$prenom == null ? other$prenom != null : !this$prenom.equals(other$prenom)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$creatAt = this.getCreatAt();
        final Object other$creatAt = other.getCreatAt();
        if (this$creatAt == null ? other$creatAt != null : !this$creatAt.equals(other$creatAt)) return false;
        final Object this$cvs = this.getCvs();
        final Object other$cvs = other.getCvs();
        if (this$cvs == null ? other$cvs != null : !this$cvs.equals(other$cvs)) return false;
        final Object this$posts = this.getPosts();
        final Object other$posts = other.getPosts();
        if (this$posts == null ? other$posts != null : !this$posts.equals(other$posts)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nom = this.getNom();
        result = result * PRIME + ($nom == null ? 43 : $nom.hashCode());
        final Object $prenom = this.getPrenom();
        result = result * PRIME + ($prenom == null ? 43 : $prenom.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $creatAt = this.getCreatAt();
        result = result * PRIME + ($creatAt == null ? 43 : $creatAt.hashCode());
        final Object $cvs = this.getCvs();
        result = result * PRIME + ($cvs == null ? 43 : $cvs.hashCode());
        final Object $posts = this.getPosts();
        result = result * PRIME + ($posts == null ? 43 : $posts.hashCode());
        return result;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", nom=" + this.getNom() + ", prenom=" + this.getPrenom() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", image=" + this.getImage() + ", role=" + this.getRole() + ", creatAt=" + this.getCreatAt() + ", cvs=" + this.getCvs() + ", posts=" + this.getPosts() + ")";
    }

    public static class UserBuilder {
        private Long id;
        private String nom;
        private String prenom;
        private String email;
        private String password;
        private String image;
        private Role role;
        private LocalDateTime creatAt;
        private List<Cv> cvs;
        private List<Post> posts;

        UserBuilder() {
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public UserBuilder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder image(String image) {
            this.image = image;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder creatAt(LocalDateTime creatAt) {
            this.creatAt = creatAt;
            return this;
        }

        public UserBuilder cvs(List<Cv> cvs) {
            this.cvs = cvs;
            return this;
        }

        public UserBuilder posts(List<Post> posts) {
            this.posts = posts;
            return this;
        }

        public User build() {
            return new User(this.id, this.nom, this.prenom, this.email, this.password, this.image, this.role, this.creatAt, this.cvs, this.posts);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", nom=" + this.nom + ", prenom=" + this.prenom + ", email=" + this.email + ", password=" + this.password + ", image=" + this.image + ", role=" + this.role + ", creatAt=" + this.creatAt + ", cvs=" + this.cvs + ", posts=" + this.posts + ")";
        }
    }
}