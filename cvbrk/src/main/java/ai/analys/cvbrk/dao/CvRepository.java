package ai.analys.cvbrk.dao;

import ai.analys.cvbrk.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {
    List<Cv> findByEtudiantId(Long etudiantId);
}
