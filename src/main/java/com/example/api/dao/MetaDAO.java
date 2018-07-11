package com.example.api.dao;

import com.example.api.domain.BoardMeta;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
@Slf4j
public class MetaDAO {
    private static final String DOMAIN = "meta.";

    @Autowired
    @Qualifier("sessionNDB")
    private SqlSession sqlSession;

    /**
     * 게시판 메타정보를 가져온다.
     *
     * @param boardId
     * @return BoardMeta
     */
    public BoardMeta getBoardMeta(String boardId) {
        BpardMeta params = new BoardMeta();
    }

}
