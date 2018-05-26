package com.example.api.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {

    @Autowired
    private JdbcTemplate template;

    public void insertNewContent(String content) {
        template.update("INSERT INTO blogs(content) VALUES(?)", content);
    }
}
