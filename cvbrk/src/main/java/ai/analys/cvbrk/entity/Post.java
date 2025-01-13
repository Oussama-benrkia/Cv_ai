package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String keyword;
    private String lien;
    private String image;
    private String email;

    @ManyToOne
    private User rh;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Postule> postulants;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;
}
