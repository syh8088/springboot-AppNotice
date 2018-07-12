package com.example.api.dao;

import com.example.api.constant.Constants;
import com.example.api.domain.BoardMeta;
import com.example.api.domain.Post;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Repository
@NoArgsConstructor
@Slf4j
public class BoardDAO {

    private static final String DOMAIN = "board.";

    @Autowired
    @Qualifier("sessionNDB")
    private SqlSession sqlSession;


    public Integer getParentPostIdByTargetId(Post post) {
        return sqlSession.selectOne(DOMAIN + "selectParentPostIdByTargetId", post);
    }

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
        if(boardMetaList.isEmpty()) {
            return null;
        }

        return boardMetaList.get(0);
    }
    /**
     * 게시판 메타 정보 목록을 조회한다. (테이블 : g4_board)
     *
     * @param boardMeta
     * @return 게시판 메타 정보 목록
     */
    public List<BoardMeta> getBoardMetaList(BoardMeta boardMeta) {
        return sqlSession.selectList(DOMAIN + "selectBoardMetaList", boardMeta);
    }

    /**
     * 게시물 정보를 조회한다.
     *
     * @param params 조회할 파라메터 (ex: boardId=free, id=392523)
     * @return 게시물 정보
     */
    public Post getPost(Post params) {

        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_BEST, params.getBoardId())) {
            params.setHasBest(true);
        }
        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_PHOTO, params.getBoardId())) {
            params.setHasPhoto(true);
        }

        if (ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_POINT, params.getBoardId())) {
            params.setPointPost(true);
        }
        if (ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_BET_STRENGTH, params.getBoardId())) {
            params.setProtoPost(true);
        }

        List<Post> postList = sqlSession.selectList(DOMAIN + "selectPost", params);
        if (postList.isEmpty()) {
            return null;
        }

    }



}
