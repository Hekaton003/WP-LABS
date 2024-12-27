package com.example.exercise_one.service.impl;

import com.example.exercise_one.model.User;
import com.example.exercise_one.model.exp.InvalidArgumentsException;
import com.example.exercise_one.repository.jpa.UserRepository;
import com.example.exercise_one.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password.isEmpty() || password == null){
            throw new InvalidArgumentsException();
        }
        return this.userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidArgumentsException::new);
    }
}
