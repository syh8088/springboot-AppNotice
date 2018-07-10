package com.example.api.controller;

import com.example.api.entities.AppNotice;
import com.example.api.entities.AppNoticeDevice;
import com.example.api.service.BoardService;
import com.example.api.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;
    //@Autowired
    //private NamedSessionStorage storage;

    @RequestMapping(value = "/appnotice", method = RequestMethod.GET)
    public ResponseEntity<?> getNotice(
            @RequestParam(required = false) AppNoticeDevice.Type deviceType,
            @RequestParam(required = false) AppNotice.Category category,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) boolean popup

            ) throws Exception {



        if(pageNo == null || pageSize == null) {
            pageNo = 0;
            pageSize = 30;
        }
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);

        return new ResponseEntity<>(boardService.getAppNoticeList(deviceType, category, pageRequest, popup), HttpStatus.OK);

    }

    /* 게시판 조회 */
    @RequestMapping(value = "/{boradId}/posts/{postId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPostAndCommentList(
        @PathVariable("boardId") String boardId,
        @PathVariable("postId") int postId,
        @RequestParam(required = false) Integer commentPageSize
    ) throws Exception {

        BoardValidator.validateBoardIdNotEmpty(boardId);
        BoardValidator.validatePostIdNotEmpty(postId);






    }





}
