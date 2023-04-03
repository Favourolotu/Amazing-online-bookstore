package com.example.bookstore.auth;

import com.example.bookstore.cart.ShoppingCart;
import com.example.bookstore.cart.ShoppingCartRepository;
import com.example.bookstore.user.User;
import com.example.bookstore.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final ShoppingCartRepository shoppingCartRepository;

    public void register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .shoppingCart(new ShoppingCart())
                .build();
        userRepository.save(user);
    }

}
