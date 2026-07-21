package com.gymflow.gymflow.user;

import com.gymflow.gymflow.user.dto.UserEmployeeRequest;
import com.gymflow.gymflow.user.dto.UserEmployeeUpdateRequest;
import com.gymflow.gymflow.user.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/create-employee")
    public ResponseEntity<UserResponse> createEmployeeUser(@RequestBody UserEmployeeRequest request) {
        UserResponse createdUser = userService.createEmployeeUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity  updateEmployeeById(@PathVariable UUID userId, @RequestBody UserEmployeeUpdateRequest request) {
        return ResponseEntity.ok(userService.updateEmployeeUserByUserId(userId, request));
    }

    @PutMapping("/{userId}/change-password")
    public ResponseEntity updateEmployeePasswordById(@PathVariable UUID userId, @RequestBody String newPassword) {
        userService.updateEmployeeUserPasswordById(userId, newPassword);
        return ResponseEntity.ok("Password updated");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteEmplyoeeById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.deleteEmployeeUserById(userId));
    }
}
