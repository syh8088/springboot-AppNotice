package com.example.api.validator;

import com.example.api.domain.BoardMeta;
import com.example.api.domain.Post;
import com.example.api.exception.NamedException;
import org.springframework.util.StringUtils;

public class BoardValidator {

    public static void validateBoardIdNotEmpty(String boardId) throws NamedException {
        if(StringUtils.isEmpty(boardId)){
            throw new NamedException("BoardIdParameterMissingError", "boardId는 필수 파라메터입니다.");
        }
    }

    /**
     * PostId 점검
     *
     * @param postId
     * @throws NamedException
     */
    public static void validatePostIdNotEmpty(int postId) throws NamedException {

        if (postId == 0) {
            throw new NamedException("PostIdParameterMissingError", "postId는 필수 파라메터입니다.");
        }
    }

    /**
     * 게시물 점검
     *
     * @param post
     * @throws NamedException
     */
    public static void validatePostIdValid(Post post) throws NamedException {

        if (post == null) {
            throw new NamedException("InvalidPostIdError", "존재하지 않는 게시물 ID입니다.");
        }
    }

    /**
     * 메타정보 점검
     *
     * @param boardMeta
     * @throws NamedException
     */
    public static void validateBoardIdValid(BoardMeta boardMeta) throws NamedException {

        if (boardMeta == null) {
            throw new NamedException("InvalidBoardIdError", "존재하지 않는 게시판 ID입니다.");
        }
    }
}
