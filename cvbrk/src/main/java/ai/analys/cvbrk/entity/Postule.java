package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Postule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double pourcentage;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User etudiant;

    // Getters et Setters
}
