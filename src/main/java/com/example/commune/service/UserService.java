package com.example.commune.service;

import com.example.commune.model.User;
import com.example.commune.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers(User user) {
        return userRepository.findAll(user);
    }

    public boolean verifyUser(String Email, String Password) {
        User user = userRepository.findByEmail(Email);
        if (user != null && passwordEncoder.matches(Password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id);
    }
}
