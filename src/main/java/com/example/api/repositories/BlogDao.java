package com.example.api.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {

    @Autowired
    private JdbcTemplate template;

    // NOTE Query 는 따로 분리하는게 좋습니다.
    public void insertNewContent(String content) {
        template.update("INSERT INTO blogs(content) VALUES(?)", content);
    }
}
