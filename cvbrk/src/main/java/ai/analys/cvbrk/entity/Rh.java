package ai.analys.cvbrk.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;


import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Rh extends User {

    @OneToMany(mappedBy = "rh", cascade = CascadeType.ALL)
    private List<Post> posts;
}
