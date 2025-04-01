package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "404", description = "User not found"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved user by ID")
    public UserEntity getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated user")
    public UserEntity updateUser(@PathVariable int userId, @RequestBody UserEntity user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted user")
    public String deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
