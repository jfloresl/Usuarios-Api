package com.jfloresl.usuarios.service;

import com.jfloresl.usuarios.repository.UserRepository;
import com.jfloresl.usuarios.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByEmail de la clase");
        User user = this.userRepository.findByEmail(email);
        if(user!=null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(Collections.emptyList()) //pasa lista vacias porque no es necesario
                    .build();
        }else{
            throw new UsernameNotFoundException("User "+ email+" not found");
        }
    }
}