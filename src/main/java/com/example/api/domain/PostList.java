package com.example.api.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 게시물 목록 Domain
 *
 * @author
 */
@NoArgsConstructor
@Setter
@ToString
@Slf4j
@JsonPropertyOrder(value = {"pageNo", "pageSize", "totalNoticeCount", "totalPostCount", "totalPageCount", "noticeList", "postList"})
public class PostList {

    // 조회 페이지 번호
    @Getter
    private Integer pageNo;

    // 조회 페이지 크기
    @Getter
    private Integer pageSize;

    // 공지사항 수
    @Getter
    private Integer totalNoticeCount;

    // 전체 조회 게시물 수
    @Getter
    private Integer totalPostCount;

    // 전체 조회 페이지 수
    private Integer totalPageCount;

    // 공지사항 목록
    @Getter
    private List<Post> noticeList;

    // 조회 게시물 목록
    @Getter
    private List<Post> postList;

    // 메인 게시물 목록
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Post> mainPostList;

    // 방채팅 1위 정보
    //@Getter
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    //private ChatRoom topChatRoom;

    public PostList(int pageNo, int ps, int totalNoticeCount, int totalPostCount, List<Post> noticeList, List<Post> postList, List<Post> mainPostList) {
        this.pageNo = pageNo;
        this.pageSize = ps;
        this.totalNoticeCount = totalNoticeCount;
        this.totalPostCount = totalPostCount;
        this.noticeList = noticeList;
        this.postList = postList;
        this.mainPostList = mainPostList;
    }

    public double getTotalPageCount() {

        return (int) Math.ceil((double) totalPostCount / pageSize);
    }
}
