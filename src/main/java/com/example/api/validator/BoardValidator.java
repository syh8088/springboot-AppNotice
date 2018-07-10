package com.example.api.validator;

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

}
