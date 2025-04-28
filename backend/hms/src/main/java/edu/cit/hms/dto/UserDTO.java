package edu.cit.hms.dto;

import edu.cit.hms.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
    private int userId;
    private String username;

    @NotBlank(message = "Password is required.")
    @Pattern(
        regexp = "^[a-zA-Z0-9!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~]{8,18}$",
        message = "Password must be 8-18 characters long and can include letters, numbers, and special characters."
    )
    private String password;

    private Roles role;
    private String email;

    public UserDTO() {}

    public UserDTO(int userId, String username, String password, Roles role, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
