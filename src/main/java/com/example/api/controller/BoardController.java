package com.example.api.controller;

import com.example.api.domain.PostAndCommentList;
import com.example.api.domain.SessionStorage;
import com.example.api.entities.AppNotice;
import com.example.api.entities.AppNoticeDevice;
import com.example.api.service.BoardService;
import com.example.api.validator.BoardValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

    @Autowired
    private SessionStorage storage;

    // FIXME 생성자 인젝션으로.
    @Autowired
    private BoardService boardService;
    //@Autowired
    //private NamedSessionStorage storage;

    // FIXME @GetMapping
//    @RequestMapping(value = "/appnotice", method = RequestMethod.GET)
    @GetMapping("/appnotice")
    public ResponseEntity<?> getNotice(
            @RequestParam(required = false) AppNoticeDevice.Type deviceType,
            @RequestParam(required = false) AppNotice.Category category,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) boolean popup

            ) throws Exception {
            // FIXME 위 requestParam 시 타입 캐스팅이나, 안들어올 경우를 대비해서 throws Exception 을 넣은 것 같은데...


        if(pageNo == null || pageSize == null) {
            pageNo = 0;
            pageSize = 30;
        }

        // FIXME PageRequest.of() 사용
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);

        return new ResponseEntity<>(boardService.getAppNoticeList(deviceType, category, pageRequest, popup), HttpStatus.OK);

    }

    /* 게시판 조회 */ // FIXME rest API?
    @RequestMapping(value = "/{boradId}/posts/{postId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPostAndCommentList(
        @PathVariable("boardId") String boardId,
        @PathVariable("postId") int postId,
        @RequestParam(required = false) Integer commentPageSize
    ) throws Exception {
//        BeanUtils.copyProperties();
        // NOTE id가 있는지 없는지 체크하는 것은 누구의 역할?
        BoardValidator.validateBoardIdNotEmpty(boardId);
        BoardValidator.validatePostIdNotEmpty(postId);

        PostAndCommentList postAndCommentList = boardService.getPostAndCommentList(boardId, postId, commentPageSize);

        return new ResponseEntity<>(postAndCommentList, HttpStatus.OK);
    }





}
