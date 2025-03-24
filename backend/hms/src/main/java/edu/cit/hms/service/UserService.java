package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity updateUser(int userId, UserEntity newUser) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User ID: " + userId + " not found!"));

        // Validate new data
        if(newUser.getUsername() != null && !newUser.getUsername().isEmpty()) {
            user.setUsername(newUser.getUsername());
        }
        if(newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
            user.setPassword(newUser.getPassword());
        }
        if(newUser.getRole() != null && !newUser.getRole().isEmpty()) {
            user.setRole(newUser.getRole());
        }
        if(newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            user.setEmail(newUser.getEmail());
        }

        // Save the updated record
        return userRepository.save(user);
    }

    public String deleteUser(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);

        if(user.isPresent()) {
            userRepository.delete(user.get());

            return "User ID: " + userId + " deleted successfully!";
        } else {
            throw new RuntimeException("User ID: " + userId + " not found!");
        }
    }
}
