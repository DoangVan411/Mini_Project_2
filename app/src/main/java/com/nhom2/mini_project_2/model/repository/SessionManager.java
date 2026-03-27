package com.nhom2.mini_project_2.model.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "movie_ticket_session";
    private static final String KEY_USER_ID = "logged_in_user_id";
    private static final long NO_USER = -1L;

    private final SharedPreferences preferences;

    public SessionManager(Context context) {
        this.preferences = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveLogin(long userId) {
        preferences.edit().putLong(KEY_USER_ID, userId).apply();
    }

    public void logout() {
        preferences.edit().remove(KEY_USER_ID).apply();
    }

    public boolean isLoggedIn() {
        return getLoggedInUserId() != NO_USER;
    }

    public long getLoggedInUserId() {
        return preferences.getLong(KEY_USER_ID, NO_USER);
    }
}
