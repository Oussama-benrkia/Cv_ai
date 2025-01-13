package ai.analys.cvbrk.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Etudiant extends User {

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Cv> cvs;

    // Getters et Setters
}
