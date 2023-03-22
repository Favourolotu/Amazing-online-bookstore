package project.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.models.Owner;
import project.persistence.OwnerRepository;
import project.persistence.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to load user from the user repository
        Optional<project.models.User> userOptional = userRepository.findUserByUsername(username);

        // If user not found, try to load owner from the owner repository
        if (userOptional.isEmpty()) {
            Optional<Owner> ownerOptional = ownerRepository.findOwnerByUsername(username);
            // If owner not found, throw an exception
            if (ownerOptional.isEmpty()) {
                throw new UsernameNotFoundException("User not found");
            }
            Owner owner = ownerOptional.get();
            // Create UserDetails object with owner details only
            return User.builder()
                    .username(owner.getUsername())
                    .password(new BCryptPasswordEncoder().encode(owner.getPassword()))
                    .roles(owner.getRoles())
                    .build();
        }

        project.models.User user = userOptional.get();
        // Create UserDetails object with user details
        return User.builder()
                .username(user.getUsername())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .roles(user.getRoles())
                .build();
    }
}


