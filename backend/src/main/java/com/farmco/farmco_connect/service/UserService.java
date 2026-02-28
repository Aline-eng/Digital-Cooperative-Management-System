package com.farmco.farmco_connect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmco.farmco_connect.model.User;
import com.farmco.farmco_connect.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String saveUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists";
        }

        userRepository.save(user);
        return "User saved successfully";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user == null) {
            return "Invalid username or password";
        }

        return "Login successful";
    }
}
