package com.gymflow.gymflow.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_domain")
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRoles role;

    UserDomain(String username, String email, String password, UserRoles role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    static UserDomain createEmployee(String username, String email, String password) {
        return new UserDomain(
                username,
                email,
                password,
                UserRoles.EMPLOYEE
        );
    }

    void updateUserEmployee(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
