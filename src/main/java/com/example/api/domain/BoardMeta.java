package com.example.api.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * 게시판 메타 정보 Domain
 *
 * @author
 */
@NoArgsConstructor
@Setter
@ToString
public class BoardMeta extends Paginator {

    @Getter
    private String boardId;

    @Getter
    private String groupId;

    @Getter
    private String description;

    @Getter
    private String shortDescription;

    @Getter
    private String adminUserId;

    @Getter
    private Integer chatLevelToReadList;

    @Getter
    private Integer chatLevelToReadPost;

    // 게시물 쓰기가 가능한 최소 레벨 (bo_write_level)
    // notice, faq 게시판의 경우 관리자 레벨인 10 이상 요구
    @Getter
    private Integer chatLevelToWritePost;

    // 게시물 답글 쓰기가 가능한 최소 레벨 (bo_reply_level)
    // 네임드는 모든 게시판의 답글 요구 레벨이 10으로 사실상 답글 기능을 사용하지 않음
    @Getter
    private Integer chatLevelToReplyPost;

    @Getter
    private Integer chatLevelToCommentPost;

    @Getter
    private Integer chatLevelToAttachPost;

    @Getter
    private Integer chatLevelToTrackbackPost;

    @Getter
    private Integer deletedCount;

    @Getter
    private Integer modifiedCount;

    @Getter
    private Integer readPoint;

    @Getter
    private Integer writePoint;

    @Getter
    private Integer writeExp;

    @Getter
    private Integer commentPoint;

    @Getter
    private Integer commentSecondPoint;

    // 게시물 추천시 지급 포인트 (bo_recommend_point)
    @Getter
    private Integer recommendPoint;

    // 카테고리 사용 여부 (bo_use_category)
    @Getter
    private Integer useCategory;

    // 카테고리 목록 (bo_category_list)
    @Getter
    private String categoryListString;

    @Getter
    private String disableTags;

    @Getter
    private Integer useSideview;

    @Getter
    private Integer useFileContent;

    @Getter
    private Integer useSecret;

    @Getter
    private Integer useDhtmlEditor;

    @Getter
    private Integer useRssView;

    @Getter
    private Integer useLike;

    @Getter
    private Integer useDislike;

    @Getter
    private Integer useName;

    @Getter
    private Integer useSignature;

    @Getter
    private Integer useTrackback;

    @Getter
    private Integer useListView;

    @Getter
    private Integer rowsPerPage;

    @Getter
    private Integer maxHourToNewPost;

    @Getter
    private Integer hitCountToBePopularPost;

    @Getter
    private Integer likeCountToBePopularPost;

    @Getter
    private String listStyle;

    @Getter
    private String skin;

    @Getter
    private String latestSkin;

    @Getter
    private String latestType;

    @Getter
    private String latestDays;

    @Getter
    private String includeHead;

    @Getter
    private String includeTail;

    @Getter
    private Integer maxAttachmentSize;

    @Getter
    private Integer maxAttachmentCount;

    @Getter
    private Integer replyOrder;

    @Getter
    private Integer useSearch;

    @Getter
    private Integer orderSearch;

    @Getter
    private Integer totalPostCount;

    @Getter
    private Integer totalCommentCount;

    @Getter
    private Integer writeMin;

    @Getter
    private Integer writeMax;

    @Getter
    @JsonIgnore
    private String noticePostListString;

    private String[] noticePostArray;

    @Getter
    private Integer minCommentCount;

    @Getter
    private Integer maxCommentCount;

    @Getter
    private String orderBy;

    @Getter
    private boolean prisonerWrite;

    @Getter
    private boolean prisonerRead;

    @Getter
    private boolean prisonerComment;

    @Getter
    private Integer limitWrite;

    @Getter
    private Integer maxCommentLength;

    @Getter
    private String isAdult;

    @Getter
    private String isAnonymous;

    @Getter
    private String editorPlugin;

    @Getter
    private Long sphinxDocStart;

    @Getter
    private String commentableAndVisibleInAdminPostMethod;

    @Getter
    @JsonIgnore
    private String commentableAdminPostListString;

    private String[] commentableAdminPostArray;

    @Getter
    private String visibleNameCard;

    @Getter
    private String visibleAd;

    @Getter
    private String isPrivate;

    @Getter
    private String isAuthRequiredToPost;

    @Getter
    private String isAuthRequiredToComment;

    @Getter
    private String simpleNotice;

    // 게시물 수 1 증가 여부
    @Getter
    private boolean plusPostCount;

    // 게시물 수 1 감소 여부
    @Getter
    private boolean minusPostCount;

    // 댓글 수 1 증가 여부
    @Getter
    private boolean plusCommentCount;

    // 댓글 수 1 감소 여부
    @Getter
    private boolean minusCommentCount;

    // 댓글 타입
    @Getter
    @Enumerated(EnumType.STRING)
    private CommentAllowedType commentAllowedType;

    // 메인글
    @Getter
    private String mainPostListString;

    // 댓글 허용 ids
    private String commentAllowedIds;


    public boolean hasCategory() {

        if (this.useCategory == null) {
            return false;
        }
        if (this.useCategory > 0) {
            return true;
        }

        return false;
    }

    public String[] getCategoryArray() {

        return StringUtils.split(this.categoryListString, "|");
    }

    public String[] getNoticePostArray() {

        return this.noticePostListString.split("\n");
    }

    public String[] getCommentableAdminPostArray() {

        return this.commentableAdminPostListString.split("\n");
    }

    public String[] getCommentAllowedIds() {

        return this.commentAllowedIds.split("\n");
    }

/*
    public String[] getMainPostArray(String category) {
        if (StringUtils.isEmpty(category)) return new String[0];
        try {
            JsonArray jsonArray = new Gson().fromJson(this.getMainPostListString(), JsonObject.class).getAsJsonArray(category);
            return new Gson().fromJson(jsonArray, String[].class);
        } catch (Exception e) {
            return new String[0];
        }
    }
*/

    /**
     * 댓글 타입을 정의한다.
     */
    public enum CommentAllowedType {
        ALWAYS, SOMETIMES, NEVER;
    }


}
