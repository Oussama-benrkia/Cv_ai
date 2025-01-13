package ai.analys.cvbrk.dao;

import ai.analys.cvbrk.entity.Postule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostuleRepository extends JpaRepository<Postule, Long> {
    List<Postule> findByEtudiantId(Long etudiantId);
    List<Postule> findByPostId(Long postId);
}
