package com.nhom2.mini_project_2.controller;

import android.content.Context;

import com.nhom2.mini_project_2.model.entity.UserEntity;
import com.nhom2.mini_project_2.model.repository.AuthRepository;

public class AuthController {
    private final AuthRepository authRepository;

    public AuthController(Context context) {
        this.authRepository = new AuthRepository(context);
    }

    public UserEntity login(String username, String password) {
        return authRepository.login(username, password);
    }

    public void logout() {
        authRepository.logout();
    }

    public boolean isLoggedIn() {
        return authRepository.isLoggedIn();
    }

    public UserEntity currentUser() {
        return authRepository.getCurrentUser();
    }
}
