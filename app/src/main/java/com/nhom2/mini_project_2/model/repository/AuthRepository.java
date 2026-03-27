package com.nhom2.mini_project_2.model.repository;

import android.content.Context;

import com.nhom2.mini_project_2.model.dao.UserDao;
import com.nhom2.mini_project_2.model.database.AppDatabase;
import com.nhom2.mini_project_2.model.entity.UserEntity;

public class AuthRepository {
    private final UserDao userDao;
    private final SessionManager sessionManager;

    public AuthRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.userDao = db.userDao();
        this.sessionManager = new SessionManager(context);
    }

    public UserEntity login(String username, String password) {
        UserEntity user = userDao.login(username, password);
        if (user != null) {
            sessionManager.saveLogin(user.id);
        }
        return user;
    }

    public void logout() {
        sessionManager.logout();
    }

    public boolean isLoggedIn() {
        return sessionManager.isLoggedIn();
    }

    public UserEntity getCurrentUser() {
        long userId = sessionManager.getLoggedInUserId();
        if (userId < 0) {
            return null;
        }
        return userDao.findById(userId);
    }
}
