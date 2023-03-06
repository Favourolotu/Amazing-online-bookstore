package project.persistence;

import project.models.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>{

    List<User> findById (int id);
    User findUserByUserName(String userName);
}
