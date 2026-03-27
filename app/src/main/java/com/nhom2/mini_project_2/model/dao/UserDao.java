package com.nhom2.mini_project_2.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nhom2.mini_project_2.model.entity.UserEntity;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(UserEntity user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    UserEntity login(String username, String password);

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    UserEntity findById(long userId);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    UserEntity findByUsername(String username);
}
