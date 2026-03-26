package com.nhom2.mini_project_2.model.database;

import androidx.room.Database;

import com.nhom2.mini_project_2.model.dao.ExampleDao;
import com.nhom2.mini_project_2.model.entity.ExampleEntity;

@Database(entities = {ExampleEntity.class}, version = 1)
public abstract class AppDatabase {
    public abstract ExampleDao exampleDao();
}
