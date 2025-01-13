package ai.analys.cvbrk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomCv;
    private String path;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creatAt;

    @ManyToOne
    private User etudiant;

}