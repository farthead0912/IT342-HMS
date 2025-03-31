package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user by ID"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UserEntity getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated user"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public UserEntity updateUser(@PathVariable int userId, @RequestBody UserEntity user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted user"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
