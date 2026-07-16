package com.gymflow.gymflow.user;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
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

    public UserDomain(
            String username,
            String email,
            String password,
            UserRoles role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserDomain createEmployee(
            String name,
            String email,
            String password) {
        return new UserDomain(
                name,
                email,
                password,
                UserRoles.EMPLOYEE
        );
    }
}
