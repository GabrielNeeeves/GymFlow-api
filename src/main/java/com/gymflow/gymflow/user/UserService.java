package com.gymflow.gymflow.user;

import com.gymflow.gymflow.exceptions.UserAlreadyExistsException;
import com.gymflow.gymflow.user.dto.UserEmployeeRequest;
import com.gymflow.gymflow.user.dto.UserEmployeeUpdateRequest;
import com.gymflow.gymflow.user.dto.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.gymflow.gymflow.helper.ValidationHelper.validate;
import static com.gymflow.gymflow.user.UserDomain.createEmployee;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse getUserById(UUID userId) {
        UserDomain currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return new UserResponse(currentUser.getUsername(), currentUser.getEmail(), currentUser.getRole().name());
    }

    public UserResponse createEmployeeUser(UserEmployeeRequest request) {
        validate(userRepository.findByEmail(request.email()).isPresent(), new UserAlreadyExistsException());

        String encodedPassword = passwordEncoder.encode(request.password());
        UserDomain user = createEmployee(request.username(), request.email(), encodedPassword);
        userRepository.save(user);

        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public UserResponse updateEmployeeUserByUserId(UUID userId, UserEmployeeUpdateRequest request) {
        UserDomain currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        currentUser.updateEmployeeUser(request.username(), request.email());
        userRepository.save(currentUser);
        return toResponse(currentUser);
    }

    public UserResponse updateEmployeeUserPasswordById(UUID userId, String newPassword) {
        UserDomain user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new IllegalArgumentException("New password must be different");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.updateEmployeeUserPassword(encodedNewPassword);
        userRepository.save(user);
        return toResponse(user);
    }

    public UserResponse deleteEmployeeUserById(UUID userId) {
        UserDomain currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        userRepository.deleteById(userId);
        return toResponse(currentUser);
    }

    private UserResponse toResponse(UserDomain userDomain) {
        return new UserResponse(
                userDomain.getUsername(),
                userDomain.getEmail(),
                userDomain.getRole().name()
        );
    }

}
