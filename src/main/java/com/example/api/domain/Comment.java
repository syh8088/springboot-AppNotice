package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * 게시물 댓글 정보 Domain
 *
 * @author
 */

@NoArgsConstructor
@Setter
@ToString
@JsonIgnoreProperties(value = {"no", "boardId", "userBannedDateString", "isUserHackedString", "pointComment"}, ignoreUnknown = true)
@JsonPropertyOrder(value = {"id", "postId", "groupId", "depth", "depth1st", "depth2nd", "depth3rd", "category", "content", "selected", "selectedPoint", "createdDateTime", "updatedDateTime", "userId", "hasProfileImage", "userProfileImageUrl", "userGrade", "userGradeDescription", "userMale", "userFemale", "userPrisoner", "userHacked", "userBanned", "userNick", "ipAddress", "device"})
public class Comment extends Paginator {

    // 댓글 ID (wr_id)
    @Getter
    private Integer id;

    // 페이징 조회시 시작 페이지의 기준으로 사용하기 위한 파라메터
    @Getter
    @JsonIgnore
    private Integer lastCommentId;

    @Getter
    @JsonIgnore
    private Integer no;

    // 게시판 ID
    @Getter
    @JsonIgnore
    private String boardId;

    // 부모 게시물 ID (wr_parent)
    @Getter
    private Integer postId;

    // 댓글 그룹 ID (wr_comment)
    @Getter
    private Integer groupId;

    // 댓글 그룹 ID 정렬 방식
    @Getter
    private String groupIdOrder;

    // 댓글 레벨 (wr_comment_reply) (null:1차, A:2차, AA:3차)
    @Getter
    private String depth;

    private boolean isDepth1st;

    private boolean isDepth2nd;

    private boolean isDepth3rd;

    // 댓글 관리자 삭제 여부 (ca_name)
    @Getter
    @JsonIgnore
    private String isDeletedString;

    private boolean isDeleted;

    // 댓글 내용
    @Getter
    private String content;

    // 포인트 게시물 댓글 여부
    @Getter
    @JsonIgnore
    private boolean isPointComment;

    // 포인트 채택 여부 (is_choice)
    // 포인트 게시판에서만 사용
    @Getter
    @JsonIgnore
    private Integer isSelectedInteger;

    // 포인트 채택 여부
    // 포인트 게시판에서만 사용
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isSelected;

    // 채택 포인트 (gift_point)
    // 포인트 게시판에서만 사용
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer selectedPoint;

    // 작성일시 (wr_datetime)
    @Getter
    private LocalDateTime createdDateTime;

    // 수정일시 (wr_last)
    @Getter
    private LocalDateTime updatedDateTime;

    // 작성자 회원 ID (mb_id)
    @Getter
    @JsonIgnore
    private String userId;

    // 작성자 닉네임 (wr_name)
    @Getter
    private String userNick;

    // 작성자 IP 주소 (wr_ip)
    @Getter
    private String ipAddress;

    // 작성자 접속 디바이스 구분 (wr_device)
    @Getter
    @JsonIgnore
    private String device;

    @Getter
    @JsonIgnore
    private Integer totalCount;

    @Getter
    @JsonIgnore
    private boolean hasTrackbackAndEmail;

    @Getter
    @JsonIgnore
    private boolean hasJsonPicks;

    @Getter
    @JsonIgnore
    private boolean hasWrRefer;
/*    @Getter
    private Writer user;*/

    public boolean isDepth1st() {

        if (StringUtils.isEmpty(this.depth)) {
            return true;
        }

        return false;
    }

    public boolean isDepth2nd() {

        if (StringUtils.isNotEmpty(this.depth) && this.depth.length() == 1) {
            return true;
        }
        return false;
    }

    public boolean isDepth3rd() {

        if (StringUtils.isNotEmpty(this.depth) && this.depth.length() == 2) {
            return true;
        }
        return false;
    }

    public boolean isDeletedByAdmin() {

        if (StringUtils.isEmpty(this.isDeletedString)) {
            return false;
        }
        if ("_REMOVE".equalsIgnoreCase(this.isDeletedString)) {
            return true;
        }
        return false;
    }

    public boolean isSelected() {

        if (this.isSelectedInteger == null) {
            return false;
        }
        if (this.isSelectedInteger == 0) {
            return false;
        }

        return true;
    }
}