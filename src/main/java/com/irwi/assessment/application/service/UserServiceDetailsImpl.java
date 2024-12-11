package com.irwi.assessment.application.service;

import com.irwi.assessment.domain.entities.UserEntity;
import com.irwi.assessment.infrastructure.persistences.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsernameOrEmail(identifier, identifier);

        if(user == null){
            throw  new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
