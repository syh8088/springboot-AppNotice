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
 * 게시물 & 댓글 목록 Domain
 *
 * @author
 */
@NoArgsConstructor
@Setter
@ToString
@Slf4j
@JsonPropertyOrder(value = {"post", "commentList", "totalCommentCount", "previousPost", "nextPost"})
public class PostAndCommentList {

    // 게시물 정보
    @Getter
    private Post post;

    // 댓글 목록
    @Getter
    private List<Comment> commentList;

    // 총 댓글 수
    @Getter
    private Integer totalCommentCount;

    // 이전 게시물
    @Getter
    private Post previousPost;

    // 다음 게시물
    @Getter
    private Post nextPost;

    // 프로토 회차 현황 정보
  /*  @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProtoRoundSummary protoRoundSummary;*/
}
