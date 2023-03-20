package project.persistence;

import project.models.ShoppingCart;
import project.models.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>{
    User findUserById(Long Id);
}
