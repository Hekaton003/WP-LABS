package com.example.exercise_one.service;

import com.example.exercise_one.model.User;

public interface AuthService {
    User login(String username, String password);
}
