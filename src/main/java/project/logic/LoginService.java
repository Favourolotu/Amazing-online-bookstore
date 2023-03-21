package project.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Owner;
import project.models.User;
import project.persistence.OwnerRepository;
import project.persistence.UserRepository;

@Service
public class LoginService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    public User validateUserCredentials(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Owner validateOwnerCredentials(String username, String password) {
        return ownerRepository.findByUsernameAndPassword(username, password);
    }
}
