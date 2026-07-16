package com.gymflow.gymflow.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) {

        try {

            System.out.println("Buscando: " + email);

            UserDomain userDomain = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(email));

            System.out.println("Usuário encontrado");
            System.out.println(userDomain.getPassword());

            return User.withUsername(userDomain.getEmail())
                    .password(userDomain.getPassword())
                    .roles(userDomain.getRole().name())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
