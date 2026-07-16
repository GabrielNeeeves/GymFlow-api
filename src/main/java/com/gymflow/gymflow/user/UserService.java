package com.gymflow.gymflow.user;

import com.gymflow.gymflow.user.dto.UserEmployeeRequest;
import com.gymflow.gymflow.user.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private UserResponse toResponse(UserDomain userDomain) {
        return new UserResponse(
                userDomain.getUsername(),
                userDomain.getEmail(),
                userDomain.getRole().name()
        );
    }

    public UserResponse createEmployeeUser(UserEmployeeRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com esse e-mail.");
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        UserDomain user = createEmployee(request.username(), request.email(), encodedPassword);
        userRepository.save(user);

        return new UserResponse(
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }

}
