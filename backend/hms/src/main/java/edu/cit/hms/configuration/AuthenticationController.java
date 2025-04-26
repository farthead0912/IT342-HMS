package edu.cit.hms.configuration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.dto.LoginDTO;
import edu.cit.hms.dto.UserDTO;
import edu.cit.hms.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiResponse(responseCode = "201", description = "Successfully registered patient")
    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encryptedPassword);

            userService.createUser(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
        }
    }

    @ApiResponse(responseCode = "201", description = "Successfully registered admin")
    @PostMapping(value = "/admin/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registerAdmin(@RequestBody UserDTO userDTO) {
        try {
            // Encrypt the password before saving
            String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encryptedPassword);

            userService.createAdmin(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Admin registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering admin: " + e.getMessage());
        }
    }

    @ApiResponse(responseCode = "201", description = "Successfully registered patient")
    @PostMapping(value = "/doctor/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registerDoctor(@RequestBody UserDTO userDTO) {
        try {
            // Encrypt the password before saving
            String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encryptedPassword);

            userService.createDoctor(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Doctor registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering doctor: " + e.getMessage());
        }
    }

    @ApiResponse(responseCode = "201", description = "Successfully registered patient")
    @PostMapping(value = "/staff/register", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registerStaff(@RequestBody UserDTO userDTO) {
        try {
            // Encrypt the password before saving
            String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encryptedPassword);

            userService.createStaff(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Staff registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering staff: " + e.getMessage());
        }
    }

    @ApiResponse(responseCode = "200", description = "Successfully logged in")
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            // Generate JWT token
            UserDTO user = userService.getUserByUsername(loginDTO.getUsername());
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole().toString(), user.getUserId());

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password."));
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Login failed, server error most likely."));
        }
    }
}
