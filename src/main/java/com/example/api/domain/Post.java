package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import org.springframework.util.StringUtils;

@NoArgsConstructor
@Setter
@ToString
@JsonIgnoreProperties(value = {"idInArray", "idNotInArray", "pointPost", "protoPost", "bestBoard"}, ignoreUnknown = true)
@JsonPropertyOrder(value = {"id", "notice", "commentable", "photo", "new", "best", "point", "pointClosed", "totalSelectedCommentCount", "totalSelectedPoint", "thumbnailUrl", "thumbnailPath", "category", "title", "commentCount", "content", "createdDateTime", "bestDateTime", "hitCount", "likeCount", "hitPopular", "likePopular", "betStrength", "protoRegistered", "userNick", "user"})
public class Post extends Paginator {
    /**
     * 게시판 카테고리를 정의한다. (post.boardId)
     */
    public enum BoardId {

        @SuppressWarnings("SpellCheckingInspection")
        MEDAL_PICK("medalpick"), // v1
        MEDAL_PICK_V2("medal_pick"); // v2

        private String value;

        BoardId(String value) {
            this.value = value;
        }

        public String getValue() {

            return value;
        }
    }

    // 게시판 ID
    @Getter
    private String boardId;
    // 게시물 ID (wr_id)
    @Getter
    private Integer id;
    // 조회할 게시물 ID 배열
    @Getter
    private String[] IdInArray;
    // 조회 제외할 게시물 ID 배열
    @Getter
    private String[] IdNotInArray;
    // 게시물 번호 (wr_no)
    @Getter
    @JsonIgnore
    private Integer no;
    // 공지사항 여부
    @Getter
    private boolean isNotice;
    // 새 글 여부
    @Getter
    private boolean isNew;
    // 첨부 이미지의 개수 (is_photo)
    @Getter
    @JsonIgnore
    private Integer isPhotoInteger;
    // 첨부 이미지 포함 여부
    private boolean isPhoto;
    // is_best
    @Getter
    @JsonIgnore
    private Integer isBestInteger;
    // 베스트 글 여부
    private boolean isBest;
    // 나눔 포인트 (포인트 게시판에서만 사용)
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer point;
    // 나눔 포인트 진행/종료 상태 (포인트 게시판에서만 사용)
    // READY, ING, COMPLETE
    @Getter
    @JsonIgnore
    private String pointStatus;
    // 채택된 총 댓글 수 (포인트 게시판에서만 사용)
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalSelectedCommentCount;
    // 채택된 총 나눔 포인트 (포인트 게시판에서만 사용)
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalSelectedPoint;
    // 나눔 포인트 종료 여부 (포인트 게시판에서만 사용)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isPointClosed;
    // 카테고리 (ca_name)
    @Getter
    private String category;
    // 이미지 썸네일 URL (포토 게시판에서만 사용)
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String thumbnailUrl;

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String thumbnailPath;

    // 글 제목 (wr_subject)
    @Getter
    private String title;
    // 글 내용 (wr_content)
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
    // 댓글 쓰기 및 출력 허용 여부
    @Getter
    private boolean isCommentable;
    // 댓글 수 (wr_comment)
    @Getter
    private Integer commentCount;
    // 작성일자 (wr_datetime)
    @Getter
    private LocalDateTime createdDateTime;
    // 베스트 선정일자 (bbs_best.regDateTime 베스트게시글일 경우에만)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    private LocalDateTime bestDateTime;
    // 조회 수
    @Getter
    private Integer hitCount;
    // 추천 수
    @Getter
    private Integer likeCount;
    // 인기 조회 여부
    @Getter
    private boolean isHitPopular;
    // 인기 추천 여부
    @Getter
    private boolean isLikePopular;
    // 작성자 회원 ID (mb_id)
    @Getter
    @JsonIgnore
    private String userId;
    // 작성자 작성 당시 닉네임 (wr_name)
    @Getter
    private String userNick;
    // 포인트 게시물 등록시 2차 비밀번호
    @Getter
    @JsonIgnore
    private String secondPassword;
    // 작성자 암호화된 암호 (wr_password)
    @Getter
    @JsonIgnore
    private String passwordEncrypted;
    // 작성자 IP 주소 (wr_ip)
    @Getter
    @JsonIgnore
    private String ipAddress;
    @Getter
    @JsonIgnore
    private String jsonPicks;
    // 프로토 게시물의 경우 강승 레벨
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int betStrength;
    // userId 조회 필터 활성화시 등록된 댓글을 포함한 게시물 목록 조회 여부
    @Getter
    @JsonIgnore
    private boolean containsComments;
    // 검색어 제목+내용
    @Getter
    @JsonIgnore
    private String titleOrContentContains;
    // 검색어 작성자 닉네임
    @Getter
    @JsonIgnore
    private String userNickEqualsTo;
    // 베스트 포함 여부
    @Getter
    @JsonIgnore
    private boolean hasBest;
    // 사진 포함 여부
    @Getter
    @JsonIgnore
    private boolean hasPhoto;
    // 포인트 게시판 글 여부
    @Getter
    @JsonIgnore
    private boolean isPointPost;
    // 프로토 게시판 글 여부
    @Getter
    @JsonIgnore
    private boolean isProtoPost;
    // 프로토 게시판 관리자 등록 여부
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isProtoRegistered;
    // 베스트 게시판 조회 여부
    @Getter
    @JsonIgnore
    private boolean isBestBoard;
    // 베스트 게시판일 경우 카테고리(ex: free, humor, photo) 조회 여부
    @Getter
    @JsonIgnore
    private boolean hasCategory;
    @Getter
    @JsonIgnore
    private boolean plusCommentCount;
    @Getter
    @JsonIgnore
    private boolean minusCommentCount;
    @Getter
    @JsonIgnore
    private boolean hasJsonPicks;
    @Getter
    @JsonIgnore
    private boolean hasWrRefer;
    // 게시글 검색범위를 정한다.
    @Getter
    @JsonIgnore
    private int targetWriteId;
    // 게시글 시작 검색범위
    @Getter
    @JsonIgnore
    private int searchMinId;
    // 게시글 마지막 검색범위
    @Getter
    @JsonIgnore
    private int searchMaxId;

    @Getter
    private Writer user;

   // @Getter
   // @JsonInclude(JsonInclude.Include.NON_NULL)
   // private MedalPick medalPick;

    @Getter
    @JsonIgnore
    private int referId;  // refer_id

    @Getter
    @JsonIgnore
    private Device device;

    // 페이징 조회시 시작 페이지의 기준으로 사용하기 위한 파라메터
    @Getter
    @JsonIgnore
    private Integer lastPostId;

    public enum Device {
        PC, MOBILE;
    }

    public boolean isPhoto() {

        if (this.isPhotoInteger == null) {
            return false;
        }
        if (this.isPhotoInteger == 0) {
            return false;
        }

        return true;
    }

    public boolean isBest() {

        if (this.isBestInteger == null) {
            return false;
        }
        if (this.isBestInteger == 0) {
            return false;
        }

        return true;
    }

    public boolean isPointClosed() {

        if (StringUtils.isEmpty(this.pointStatus)) {
            return false;
        }
        if (this.pointStatus.equalsIgnoreCase("COMPLETE")) {
            return true;
        }

        return false;
    }

}
