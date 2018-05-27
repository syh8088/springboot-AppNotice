package com.example.api.controller;

import com.example.api.entities.AppNotice;
import com.example.api.entities.AppNoticeDevice;
import com.example.api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
