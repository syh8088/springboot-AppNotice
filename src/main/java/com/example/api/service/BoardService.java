package com.example.api.service;

import com.example.api.constant.Constants;
import com.example.api.dao.BoardDAO;
import com.example.api.dao.MetaDAO;
import com.example.api.domain.*;
import com.example.api.entities.AppNotice;
import com.example.api.entities.AppNoticeDevice;
import com.example.api.exception.NamedException;
import com.example.api.repositories.AppNoticeDeviceRepository;
import com.example.api.validator.BoardValidator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardDAO boardDAO;

    @Autowired
    private MetaDAO metaDAO;

    @Autowired
    private AppNoticeDeviceRepository appNoticeDeviceRepository;

    public List<AppNoticeDevice> getAppNoticeList(AppNoticeDevice.Type deviceType, AppNotice.Category category, Pageable pageable, boolean popup) throws Exception {

        System.out.println(deviceType);
        System.out.println(category);
        System.out.println(pageable);
        System.out.println(popup);


        List<AppNoticeDevice> noticeList;
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        noticeList =
                appNoticeDeviceRepository.
                        findByTypeAndAppNotice_CategoryAndAppNotice_ReserveAtBeforeOrderByAppNotice_ReserveAtDesc
                                (deviceType, category, localDateTime, pageable);

//
//        noticeList = appNoticeDeviceRepository.findByTypeAndPopupAllowedAndAppNotice_ReserveAtBeforeAndPopupStartDateBeforeAndPopupEndDateAfterOrderByAppNotice_ReserveAtDesc
//                (deviceType, popup, localDateTime, localDateTime, localDateTime, pageable);

        System.out.println(noticeList);
        return noticeList;
    }


    /**
     * 댓글을 포함한 게시물 정보를 반환한다.
     *
     * @param boardId         게시판 ID (ex:free)
     * @param postId          게시글 ID (ex: 3885921)
     * @param commentPageSize 페이징크기
     * @return 댓글이 포함된 게시물 정보
     */
    public PostAndCommentList getPostAndCommentList(String boardId, int postId, Integer commentPageSize) throws NamedException {
        Post params = new Post();
        params.setBoardId(boardId);
        params.setId(postId);
        params.setPageSize(commentPageSize);

        Integer id = boardDAO.getParentPostIdByTargetId(params);
        if (id == null)
            throw new NamedException("NotExistPostIdError", "존재하지 않은 게시글 번호입니다.");

        // postId가 commnentID로 들어왔을 경우 parentID를 반환한다. PC/Mobile 불일치문제
        params.setId(boardDAO.getParentPostIdByTargetId(params));

        return getPostAndCommentList(params);
    }

    /**
     * 댓글을 포함한 게시물 정보를 반환한다.
     *
     * @param params 조회할 파라메터 (ex: boardId=free, id=3885921)
     * @return 댓글이 포함된 게시물 정보
     */
    public PostAndCommentList getPostAndCommentList(Post params) throws NamedException {
        BoardMeta boardMeta = metaDAO.getBoardMeta(params.getBoardId());

        Comment comment = new Comment();
        comment.setBoardId(params.getBoardId());
        comment.setPostId(params.getId());
        if(params.getPageSize() > 1) {
            comment.setPageNo(1);
            comment.setPageSize(params.getPageSize());
        }

        params.setPageSize(null);
        if(!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_BEST, params.getBoardId())) {
            params.setHasBest(true);
        }

        if(!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_PHOTO, params.getBoardId())) {
            params.setHasPhoto(true);
        }

        if(!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_POINT, params.getBoardId())) {
            params.setPointPost(true);
        }

        Post post = boardDAO.getPost(params);
        BoardValidator.validatePostIdValid(post);
        post = calculatePost(post, boardMeta);

        PostAndCommentList postAndCommentList = new PostAndCommentList();
        postAndCommentList.setPost(post);
        postAndCommentList.setCommentList(boardDAO.getCommentList(comment));
        postAndCommentList.setTotalCommentCount(boardDAO.getCommentTotalCount(comment));
        postAndCommentList.setPreviousPost(getPostByNo(params.getBoardId(), boardDAO.getPreviousPostByWriteId(post)));
        postAndCommentList.setNextPost(getPostByNo(params.getBoardId(), boardDAO.getNextPostByWriteId(post)));

        //if (ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_BET_STRENGTH, post.getBoardId())) {
        //    postAndCommentList.setProtoRoundSummary(protoDAO.getProtoRoundSummary());
        //}

        //if (!Post.BoardId.MEDAL_PICK_V2.getValue().equalsIgnoreCase(post.getBoardId())) {
        //    if (StringUtils.isNotEmpty(storage.getIpAddress()) && !boardDAO.isPostReadToday(post.getBoardId(), post.getId(), storage.getIpAddress())) {
                boardDAO.updatePostHitCount(post.getBoardId(), post.getId());
        //        log.info("POST_HIT_COUNT_UPDATED");
        //    }
        //}


        return postAndCommentList;
    }

    /**
     * 게시판 기준정보를 이용하여 가공된 게시물 정보를 반환한다.
     * 덧글 쓰기 및 보기 권한 부분 추가
     *
     * @param post      가공할 게시물 오브젝트
     * @param boardMeta 게시판 기준정보
     * @return 가공된 게시물 오브젝트
     */
    private Post calculatePost(Post post, BoardMeta boardMeta) {

        if(post == null) {
            return null;
        }

        for(String noticeId : boardMeta.getNoticePostArray()) {
            if(StringUtils.isEmpty(noticeId)) {
                continue;
            }
            if(post.getId() == Integer.parseInt(noticeId)) {
                post.setNotice(true);
            }
        }

        // 메타정보에 등록된 게시글에 대한 덧글 권한 설정
        if(!post.getUser().isAdmin() || BoardMeta.CommentAllowedType.ALWAYS == boardMeta.getCommentAllowedType()) {
            post.setCommentable(false);
        } else if(BoardMeta.CommentAllowedType.NEVER == boardMeta.getCommentAllowedType()) {
            post.setCommentable(false);
        } else if(BoardMeta.CommentAllowedType.SOMETIMES == boardMeta.getCommentAllowedType()) {
            for (String id : boardMeta.getCommentAllowedIds()) {
                if(StringUtils.isEmpty(id))
                    continue;

                if(post.getId() == Integer.parseInt(id)) {
                    post.setCommentable(true);
                }
            }
        }

        // 새 글 여부 설정
        if (boardMeta.getMaxHourToNewPost() != null && LocalDateTime.now().minusHours(boardMeta.getMaxHourToNewPost()).isBefore(post.getCreatedDateTime())) {
            post.setNew(true);
        }

        // 포토, 스포츠 뉴스, 매치 프리뷰 게시판의 경우 썸네일 URL 설정
        if (!post.isNotice() && "gall".equalsIgnoreCase(boardMeta.getListStyle()) || "sports".equals(post.getBoardId()) || "match".equals(post.getBoardId())) {
            //post.setThumbnailUrl(getPostThumbnailUrl(post.getBoardId(), post.getId()));
            //post.setThumbnailPath(getPostThumbnailPath(post.getBoardId(), post.getId()));
        }

        // 첨부 이미지 경로의 HOSTNAME이 2번 생성되었을 경우 수정
        // post.setContent(StringUtils.replace(post.getContent(), namedDataRootUrl + namedDataRootUrl, namedDataRootUrl));

        long userExp = 0;
        long userPoint = 0;
        if (post.getUser().getExp() != null) {
            userExp = post.getUser().getExp();
        }
        if (post.getUser().getPoint() != null) {
            userPoint = post.getUser().getPoint();
        }
        //UserGrade userGrade = userDAO.calculateUserGrade(post.getUserId(), userExp, userPoint);
        //post.getUser().setGrade(userGrade.getUserGrade());
        //post.getUser().setGradeDescription(userGrade.getDescription());

        if (post.getHitCount() >= boardMeta.getHitCountToBePopularPost()) {
            post.setHitPopular(true);
        }
        if (post.getLikeCount() >= boardMeta.getLikeCountToBePopularPost()) {
            post.setLikePopular(true);
        }
        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_POINT, post.getBoardId())) {
            post.setIsPointClosed(null);
        }
        return post;
    }


    /**
     * 게시물 번호(Post.No)를 기준으로 게시물 정보를 반환한다. (내용, 댓글 목록 제외)
     * 이전 게시물(+1), 다음 게시물(-1) 조회시 주로 사용한다.
     *
     * @param boardId 게시판 분류
     * @param postNo  게시물 번호
     * @return 게시물 정보
     */
    public Post getPostByNo(String boardId, Integer postNo) throws NamedException {

        if (postNo == null) {
            return null;
        }

        Post params = new Post();
        params.setBoardId(boardId);
        params.setNo(postNo);

        PostList postList = getPostList(params, false);
        if (postList.getPostList().isEmpty()) {
            return null;
        }

        return postList.getPostList().get(0);
    }

    /**
     * 게시물 목록을 반환한다.
     * 베스트 게시판의 경우 boardId=best 로 조회한다.
     *
     * @param post           조회할 파라메터 (ex: boardId=ladder, category=사다리, pageNo=1, pageSize=20)
     * @param containsNotice 조회시 공지사항 포함 여부
     * @return 게시물 목록
     */
    public PostList getPostList(Post post, boolean containsNotice) throws NamedException {

        // 게시판 기준정보 조회
        BoardMeta boardMeta = metaDAO.getBoardMeta(post.getBoardId());

        // 베스트 게시판 조회시 처리
        if ("best".equalsIgnoreCase(post.getBoardId())) {
            post.setBestBoard(true);
            post.setBoardId(post.getCategory());
            if (StringUtils.isNotEmpty(post.getCategory())) {
                post.setHasCategory(true);
                if (!Arrays.asList(Constants.BEST_BOARD_ID_ARRAY).contains(post.getCategory())) {

                    throw new NamedException("InvalidBestBoardIdError", "존재하지 않는 베스트 게시판입니다.");
                }
            }
        }
        BoardValidator.validateBoardIdValid(boardMeta);

        // 첨부 이미지 존재 및 베스트 선정 여부 설정
        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_BEST, post.getBoardId())) {
            post.setHasBest(true);
        }
        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_PHOTO, post.getBoardId())) {
            post.setHasPhoto(true);
        }

        // 페이지 번호, 크기 설정
        if (post.getPageSize() == null || post.getPageSize() == 0) {
            post.setPageSize(boardMeta.getRowsPerPage());
        }

        if (post.getPageNo() == null || post.getPageNo() == 0) {
            post.setPageNo(1);
        }

        if (!post.isBestBoard() && ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_POINT, post.getBoardId())) {
            post.setPointPost(true);
        }

        // 검색어 설정
        if (StringUtils.isNotEmpty(post.getTitleOrContentContains())) {
            post.setUserNickEqualsTo(null);
        }

        // 공지사항 및 게시물 목록 조회
        List<Post> noticeList = new ArrayList<>();
        if (!post.isBestBoard() && containsNotice) {
            post.setIdInArray(boardMeta.getNoticePostArray());
            noticeList = calculatePostList(boardDAO.getNoticeList(post), boardMeta);
            post.setIdInArray(null);
        }
        post.setIdNotInArray(boardMeta.getNoticePostArray());

        // 메인 게시글
        List<Post> mainPostList = new ArrayList<>();
        if (StringUtils.isNotEmpty(boardMeta.getMainPostListString())) {
            //post.setIdInArray(boardMeta.getMainPostArray(post.getCategory()));
            mainPostList = calculatePostList(boardDAO.getMainPostList(post), boardMeta);
            post.setIdInArray(null);
        }

        List<Post> postList = calculatePostList(boardDAO.getPostList(post), boardMeta);

        int totalPostCount = boardDAO.getPostTotalCount(post);

        return new PostList(post.getPageNo(), post.getPageSize(), noticeList.size(), totalPostCount, noticeList, postList, mainPostList);
    }


    private List<Post> calculatePostList(List<Post> postList, BoardMeta boardMeta) {

        List<Post> newPostList = new ArrayList<>();
        if (StringUtils.isEmpty(boardMeta.getBoardId())) {
            return postList;
        }
        for (Post post : postList) {
            newPostList.add(calculatePost(post, boardMeta));
        }

        return newPostList;
    }
}
