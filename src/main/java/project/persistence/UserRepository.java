package project.persistence;

import org.springframework.data.repository.CrudRepository;
import project.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
