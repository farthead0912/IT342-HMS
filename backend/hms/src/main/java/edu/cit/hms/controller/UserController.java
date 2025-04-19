package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.dto.UserDTO;
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
@RequestMapping(value = "/api/user", produces = "application/json", consumes = "application/json")
public class UserController {
    @Autowired
    private UserService userService;

    // Gets all users
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    // Gets user by ID
    @GetMapping(value = "/{userId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved user by ID")
    public UserDTO getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    // Updates user details by ID
    @PutMapping(value = "/{userId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated user")
    public UserEntity updateUser(@PathVariable int userId, @RequestBody UserDTO user) {
        return userService.updateUser(userId, user);
    }


    // Deletes user by ID
    @DeleteMapping(value = "/{userId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted user")
    public String deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
