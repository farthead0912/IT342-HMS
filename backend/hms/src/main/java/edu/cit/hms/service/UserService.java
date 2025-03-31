package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<UserEntity> createUser(UserEntity user) {
        try {
            UserEntity createdUser = userRepository.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    public ResponseEntity<UserEntity> getUserById(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    public ResponseEntity<List<UserEntity>> getUsers() {
        try {
            List<UserEntity> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    public ResponseEntity<UserEntity> updateUser(int userId, UserEntity newUser) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Validate and update fields
            if (newUser.getUsername() != null && !newUser.getUsername().isEmpty()) {
                user.setUsername(newUser.getUsername());
            }
            if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
                user.setPassword(newUser.getPassword());
            }
            if (newUser.getRole() != null && !newUser.getRole().isEmpty()) {
                user.setRole(newUser.getRole());
            }
            if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
                user.setEmail(newUser.getEmail());
            }

            UserEntity updatedUser = userRepository.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    public ResponseEntity<String> deleteUser(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity<>("User ID: " + userId + " deleted successfully!", HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>("User ID: " + userId + " not found!", HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}
