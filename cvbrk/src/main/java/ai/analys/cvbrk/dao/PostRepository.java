package ai.analys.cvbrk.dao;

import ai.analys.cvbrk.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByRhId(Long rhId);
}
