package com.nhom2.mini_project_2.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = "username", unique = true)})
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    public String username;

    @NonNull
    public String password;

    @NonNull
    public String fullName;

    public UserEntity() {
    }

    public UserEntity(@NonNull String username, @NonNull String password, @NonNull String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }
}
