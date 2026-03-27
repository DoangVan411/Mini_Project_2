package com.nhom2.mini_project_2.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "theaters")
public class TheaterEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    public String name;

    @NonNull
    public String address;

    public TheaterEntity(@NonNull String name, @NonNull String address) {
        this.name = name;
        this.address = address;
    }
}
