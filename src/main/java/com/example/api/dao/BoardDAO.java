package com.example.api.dao;

import com.example.api.domain.Post;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

@Repository
@NoArgsConstructor
@Slf4j
public class BoardDAO {
    @Autowired
    @Qualifier("sessionNDB")
    private SqlSession sqlSession;


    public Integer getParentPostIdByTargetId(Post post) {
        return sqlSession.selectOne()
    }



}
