package ai.analys.cvbrk.dao;

import ai.analys.cvbrk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RhRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
}