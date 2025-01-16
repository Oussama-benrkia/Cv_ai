package ai.analys.cvbrk.dao;

import ai.analys.cvbrk.entity.Role;
import ai.analys.cvbrk.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    long countByRole(Role role);
    boolean existsByEmail(String email);
    List<User> findAllByRole(String role);
    List<User> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(String nom, String prenom,String email);
    Page<User> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCase(String nom, String prenom, String email, Pageable pageable);
    Page<User> findAllByRole(String role, Pageable pageable);

    Optional<User> findByEmail(String email);

    List<User> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCaseAndRole(
            String nom, String prenom, String email, Role role);

    Page<User> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCaseOrEmailContainingIgnoreCaseAndRole(
            String nom, String prenom, String email, Role role, Pageable pageable);


}
