package com.example.api.dao;

import com.example.api.domain.BoardMeta;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        BoardMeta params = new BoardMeta();
        params.setBoardId(boardId);
        List<BoardMeta> boardMetaList = getBoardMetaList(params);
        if (boardMetaList.isEmpty()) {
            return null;
        }

        return boardMetaList.get(0);
    }

    /**
     * 게시판 메타 정보 목록을 조회한다. (neolive.g4_board)
     *
     * @param boardMeta
     * @return 게시판 메타 정보 목록
     */
    public List<BoardMeta> getBoardMetaList(BoardMeta boardMeta) {

        return sqlSession.selectList(DOMAIN + "selectBoardMetaList", boardMeta);
    }

}
