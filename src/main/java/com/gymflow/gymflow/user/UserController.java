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

    @PostMapping("/create-employee")
    public ResponseEntity<UserResponse> createEmployeeUser(@RequestBody UserEmployeeRequest request) {
        UserResponse createdUser = userService.createEmployeeUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity  updateEmployeeById(@PathVariable UUID userId, @RequestBody UserEmployeeUpdateRequest request) {
        return ResponseEntity.ok(userService.updateEmployeeUserByUserId(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteEmplyeeById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.deleteEmployeeUserById(userId));
    }
}
