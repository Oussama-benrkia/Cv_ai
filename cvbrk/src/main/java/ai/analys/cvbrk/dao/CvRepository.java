package ai.analys.cvbrk.dao;

import ai.analys.cvbrk.entity.Cv;
import ai.analys.cvbrk.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {
    List<Cv> findAllByEtudiant(User etudiantId);
    Page<Cv> findAllByEtudiant(User etudiantId, Pageable pageable);

    Page<Cv> findAllByEtudiantAndTitreContainingIgnoreCase(User etudiantId, String titre, Pageable pageable);
    List<Cv> findAllByEtudiantAndTitreContainingIgnoreCase(User etudiantId, String titre);

}
