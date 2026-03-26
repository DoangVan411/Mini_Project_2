package com.nhom2.mini_project_2.model.repository;

import com.nhom2.mini_project_2.model.dao.ExampleDao;

public class ExampleRepository {
    private final ExampleDao exampleDao;

    public ExampleRepository(ExampleDao exampleDao) {
        this.exampleDao = exampleDao;
    }
}
