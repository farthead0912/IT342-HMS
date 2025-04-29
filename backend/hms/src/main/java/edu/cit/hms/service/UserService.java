package edu.cit.hms.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.UserDTO;
import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.enums.Roles;
import edu.cit.hms.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserEntity createUser(UserDTO userDTO) {
        UserEntity user = convertFromDTO(userDTO);

        user.setRole(Roles.PATIENT); // Default role is PATIENT

        return userRepository.save(user); // Save the patient user to the database
    }

    public UserEntity createStaff(UserDTO userDTO) {
        UserEntity user = convertFromDTO(userDTO); // Converts DTO to entity

        user.setRole(Roles.STAFF); // Default role is STAFF

        return userRepository.save(user); // Save the staff user to the database
    }

    public UserEntity createDoctor(UserDTO userDTO) {
        UserEntity user = convertFromDTO(userDTO);

        user.setRole(Roles.DOCTOR); // Default role is DOCTOR

        return userRepository.save(user); // Save the doctor user to the database
    }

    public UserEntity createAdmin(UserDTO userDTO) {
        UserEntity user = convertFromDTO(userDTO);

        user.setRole(Roles.ADMIN); // Default role is ADMIN

        return userRepository.save(user); // Save the admin user to the database
    }

    public UserDTO getUserById(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        
        return user.map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("User ID: " + userId + " not found!")); // Return the user if present, otherwise null
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Email: " + email + " not found!"));
    }

    public UserDTO getUserByRole(Roles role) {
        return userRepository.findByRole(role)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Role: " + role + " not found!"));
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Username: " + username + " not found!"));
    }

    public List<UserDTO> getUsers() {
        try {
            List<UserEntity> users = userRepository.findAll();

            return users.stream()
                .map(this::convertToDTO)
                .toList();
        } catch (DataAccessException e) {
            // Log the exception
            System.err.println("Database error occurred while fetching users: " + e.getMessage());
            throw new RuntimeException("Unable to fetch users at this time. Please try again later.");
        }
    }

    public UserEntity updateUser(int userId, UserDTO newUser) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Validate and update fields
            if (newUser.getUsername() != null && !newUser.getUsername().isEmpty()) {
                user.setUsername(newUser.getUsername());
            }
            if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(newUser.getPassword())); // Encrypt the password
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

    private UserEntity convertFromDTO(UserDTO userDTO) {
        UserEntity user = new UserEntity();
		
		user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());

        return user; // Convert DTO to entity
    }

    private UserDTO convertToDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();

		userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setEmail(user.getEmail());

        return userDTO; // Convert entity to DTO
    }
}
