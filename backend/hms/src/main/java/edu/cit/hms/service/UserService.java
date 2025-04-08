package edu.cit.hms.service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.UserDTO;
import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.enums.Roles;
import edu.cit.hms.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Roles.PATIENT); // Default role is PATIENT
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user); // Save the patient user to the database
    }

    public UserEntity createStaff(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Roles.STAFF); // Default role is STAFF
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user); // Save the staff user to the database
    }

    public UserEntity createDoctor(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Roles.DOCTOR); // Default role is DOCTOR
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user); // Save the doctor user to the database
    }

    public UserEntity createAdmin(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Roles.ADMIN);
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user); // Save the admin user to the database
    }

    public UserEntity getUserById(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        return user.orElse(null); // Return the user if present, otherwise null
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<UserEntity> getUsers() {
        try {
            return userRepository.findAll(); // Return the list of users
        } catch (Exception e) {
            return null; // Return null in case of an error
        }
    }

    public UserEntity updateUser(int userId, UserEntity newUser) {
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
            if (newUser.getRole() != null && EnumSet.allOf(Roles.class).contains(newUser.getRole())) {
                user.setRole(newUser.getRole());
            } else if (newUser.getRole() != null) {
                throw new IllegalArgumentException("Invalid role: " + newUser.getRole());
            }
            if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
                user.setEmail(newUser.getEmail());
            }

            return userRepository.save(user); // Return the updated user
        } else {
            return null; // Return null if the user is not found
        }
    }

    public String deleteUser(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User ID: " + userId + " deleted successfully!"; // Return success message
        } else {
            return "User ID: " + userId + " not found!"; // Return not found message
        }
    }
}
