package project.persistence;

import org.springframework.data.repository.CrudRepository;
import project.models.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
