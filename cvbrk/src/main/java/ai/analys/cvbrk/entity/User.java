package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public  class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String password;
    private String image;
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;

    @OneToMany( mappedBy = "etudiant",cascade = CascadeType.ALL)
    private List<Cv> cvs;
    @OneToMany(mappedBy = "rh",cascade = CascadeType.ALL)
    private List<Post> posts;



    // Getters et Setters
}
