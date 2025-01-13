package ai.analys.cvbrk.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRequest {
    @NotBlank(message = "Name must not be blank")
    private String nom;
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
    private String image;
    public UserRequest() {
    }
    public UserRequest(String nom, String email, String password, String image) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.image = image;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}