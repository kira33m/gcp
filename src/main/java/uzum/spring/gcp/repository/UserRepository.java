package uzum.spring.gcp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uzum.spring.gcp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPinfl(String pinfl);

    boolean existsByPinfl(String pinfl);
}
