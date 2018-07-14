package com.example.api.dao;

import com.example.api.constant.Constants;
import com.example.api.domain.BoardMeta;
import com.example.api.domain.Comment;
import com.example.api.domain.Post;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
@Slf4j
public class BoardDAO { // FIXME DAO 가 너무 많은 일은 하고 있습니다.
    private static final int DEFAULT_POST_SCAN_RANGE = 10000;
    private static final String DOMAIN = "board.";

    //@Autowired
    //private UserDAO userDAO;

    @Autowired
    @Qualifier("sessionNDB")
    private SqlSession sqlSession;


    public Integer getParentPostIdByTargetId(Post post) {
        return sqlSession.selectOne(DOMAIN + "selectParentPostIdByTargetId", post);
    }

    /**
     * 게시판 메타정보를 가져온다.
     *
     * @param boardId
     * @return BoardMeta
     */
    public BoardMeta getBoardMeta(String boardId) {
        BoardMeta params = new BoardMeta();
        params.setBoardId(boardId);
        List<BoardMeta> boardMetaList = getBoardMetaList(params);
        if(boardMetaList.isEmpty()) {
            return null;
        }

        return boardMetaList.get(0);
    }
    /**
     * 게시판 메타 정보 목록을 조회한다. (테이블 : g4_board)
     *
     * @param boardMeta
     * @return 게시판 메타 정보 목록
     */
    public List<BoardMeta> getBoardMetaList(BoardMeta boardMeta) {
        return sqlSession.selectList(DOMAIN + "selectBoardMetaList", boardMeta);
    }

    /**
     * 게시물 정보를 조회한다.
     *
     * @param params 조회할 파라메터 (ex: boardId=free, id=392523)
     * @return 게시물 정보
     */
    public Post getPost(Post params) {

        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_BEST, params.getBoardId())) {
            params.setHasBest(true);
        }
        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_PHOTO, params.getBoardId())) {
            params.setHasPhoto(true);
        }

        if (ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_POINT, params.getBoardId())) {
            params.setPointPost(true);
        }
        if (ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_BET_STRENGTH, params.getBoardId())) {
            params.setProtoPost(true);
        }

        List<Post> postList = sqlSession.selectList(DOMAIN + "selectPost", params);
        if (postList.isEmpty()) {
            return null;
        }

        Post post = postList.get(0);
        //UserGrade userGrade = userDAO.calculateUserGrade(post.getUserId(), post.getUser().getExp(), post.getUser().getPoint());
        //post.getUser().setGrade(userGrade.getUserGrade());
        //post.getUser().setGradeDescription(userGrade.getDescription());
        return post;

    }

    /**
     * 게시물 댓글 목록을 조회한다.
     *
     * @param params 조회할 파라메터 (ex: boardId, postId)
     * @return 댓글 목록
     */
    public List<Comment> getCommentList(Comment params) {

        if (StringUtils.isEmpty(params.getBoardId()) && params.getPostId() == null) {
            return new ArrayList<>();
        }
        if (ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_POINT, params.getBoardId())) {
            params.setPointComment(true);
        }
        List<Comment> commentList = sqlSession.selectList(DOMAIN + "selectCommentList", params);
        //List<Comment> newCommentList = new ArrayList<>();
        //for (Comment comment : commentList) {
        //    newCommentList.add(calculateComment(comment));
        //}

        return commentList;
    }

/*    private Comment calculateComment(Comment comment) {

        UserGrade userGrade = userDAO.calculateUserGrade(comment.getUserId(), comment.getUser().getExp(), comment.getUser().getPoint());
        comment.getUser().setGrade(userGrade.getUserGrade());
        comment.getUser().setGradeDescription(userGrade.getDescription());
        return comment;
    }*/

    /**
     * 게시물 댓글 수를 조회한다.
     *
     * @param params 조회할 파라메터 (ex: boardId, postId)
     * @return 댓글 목록
     */
    public Integer getCommentTotalCount(Comment params) {

        params.setCountable(true);
        List<Comment> commentList = sqlSession.selectList(DOMAIN + "selectCommentList", params);

        if (commentList.isEmpty()) {
            return 0;
        }

        return commentList.get(0).getTotalCount();
    }

    /**
     * 게시물의 이전글ID를 반환한다.
     *
     * @param post
     * @return
     */
    public Integer getPreviousPostByWriteId(Post post) {

        return sqlSession.selectOne(DOMAIN + "selectPreviousPostId", post);
    }

    /**
     * 게시물의 다음글ID를 반환한다.
     *
     * @param post
     * @return
     */
    public Integer getNextPostByWriteId(Post post) {

        return sqlSession.selectOne(DOMAIN + "selectNextPostId", post);
    }

    public int countByBoardIdAndUserIdWithinToday(final Post post) {
        return sqlSession.selectOne(DOMAIN + "countByBoardIdAndUserIdWithinToday", post);
    }

    /**
     * 게시물 조회수를 1 증가시킨다.
     *
     * @param boardId 게시판 ID
     * @param postId  게시물 ID
     * @return int The number of rows affected by the update.
     */
    public int updatePostHitCount(String boardId, int postId) {
        log.debug("updatePostHitCount boardId: {}, postId: {}", boardId, postId);
        Post params = new Post();
        params.setBoardId(boardId);
        params.setId(postId);

        return sqlSession.update(DOMAIN + "updatePostHitCount", params);
    }

    /**
     * 최근의 게시글을 반환한다.
     *
     * @param post
     * @return
     */
    private int getLastWriteId(Post post) {

        return sqlSession.selectOne(DOMAIN + "selectLastWriteId", post);
    }

    /**
     * 게시글의 ID를 반환한다.
     *
     * @param post
     * @return
     */
    private int getIdByWriteId(Post post) {

        return sqlSession.selectOne(DOMAIN + "selectIdByWriteId", post);
    }

    /**
     * 게시판 공지사항 목록을 조회한다.
     *
     * @param post 조회할 파라메터 (ex: boardId, idInArray)
     * @return 게시글 목록
     */
    public List<Post> getNoticeList(Post post) {

        Post params = new Post();
        params.setBoardId(post.getBoardId());
        params.setIdInArray(post.getIdInArray());
        params.setHasPhoto(post.isHasPhoto());

        List<Post> noticeList = new ArrayList<>();
        if (params.getIdInArray().length > 0) {
            noticeList = sqlSession.selectList(DOMAIN + "selectPostList", params);
        }

        return noticeList;
    }

    /**
     * 게시판 메인글 목록을 조회한다.
     *
     * @param post 조회할 파라메터 (ex: boardId, idInArray)
     * @return 게시글 목록
     */
    public List<Post> getMainPostList(Post post) {

        Post params = new Post();
        params.setBoardId(post.getBoardId());
        params.setIdInArray(post.getIdInArray());
        params.setCategory(post.getCategory());

        List<Post> mainPostList = new ArrayList<>();
        if (params.getIdInArray().length > 0) {
            mainPostList = sqlSession.selectList(DOMAIN + "selectPostList", params);
        }

        return mainPostList;
    }

    /**
     * 조건에 따른 조회 쿼리문을 결정한다.
     *
     * @param isBestBoard 베스트 게시판 조회 여부
     * @param hasCategory 베스트 게시판이면서 카테고리(ex: free, humor, photo) 존재 여부
     * @return
     */
    private String SELECT_BOARD_POST_LIST(boolean isBestBoard, boolean hasCategory) {

        if (isBestBoard) {
            if (hasCategory) {
                return "selectBestPostList";
            }
            return "selectAllCategoriesBestPostList";
        }

        return "selectPostList";
    }

    /**
     * 조건에 따른 게시물 수를 조회한다.
     *
     * @param params 조회할 파라메터 (ex: pageNo, pageSize)
     * @return 회원 수
     */
    public Integer getPostTotalCount(Post params) {

        params.setCountable(true);
        List<Post> postList = sqlSession.selectList(DOMAIN + SELECT_BOARD_POST_LIST(params.isBestBoard(), params.isHasCategory()), params);
        if (postList.isEmpty()) {
            return 0;
        }
        if (params.isContainsComments()) {
            return postList.size();
        }

        return postList.get(0).getTotalCount();
    }


    /**
     * 게시글 목록을 조회한다.
     *
     * @param params 조회할 파라메터 (ex: boardId, pageNo, pageSize)
     * @return 게시글 목록
     */
    public List<Post> getPostList(Post params) {

        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_BEST, params.getBoardId())) {
            params.setHasBest(true);
        }
        if (!ArrayUtils.contains(Constants.BOARD_ID_ARRAY_NOT_CONTAINS_PHOTO, params.getBoardId())) {
            params.setHasPhoto(true);
        }
        if (ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_POINT, params.getBoardId())) {
            params.setPointPost(true);
        }
        if (ArrayUtils.contains(Constants.BOARD_ID_ARRAY_CONTAINS_BET_STRENGTH, params.getBoardId())) {
            params.setProtoPost(true);
        }

        // 일반 게시판일 경우 최근 2만건에 대해서만 검색한다.
        if (!params.isBestBoard()) {
            int targetWriteId = this.getLastWriteId(params);

            params.setTargetWriteId(targetWriteId);
            params.setSearchMaxId(this.getIdByWriteId(params));

            params.setTargetWriteId(targetWriteId + DEFAULT_POST_SCAN_RANGE);
            params.setSearchMinId(this.getIdByWriteId(params));
        }

        List<Post> postList = sqlSession.selectList(DOMAIN + SELECT_BOARD_POST_LIST(params.isBestBoard(), params.isHasCategory()), params);

        // 베스트 게시판 전체 조회일 경우 처리
        if (params.isBestBoard() && !params.isHasCategory()) {
            List<Post> bestPostList = new ArrayList<>();
            for (Post newPost : postList) {
                List<Post> newPostList = sqlSession.selectList(DOMAIN + "selectAllCategoriesBestPost", newPost);
                if (newPostList.isEmpty()) {
                    continue;
                }
                newPost.setNo(newPostList.get(0).getNo());
                newPost.setIsPhotoInteger(newPostList.get(0).getIsPhotoInteger());
                newPost.setIsBestInteger(newPostList.get(0).getIsBestInteger());
                newPost.setTitle(newPostList.get(0).getTitle());
                newPost.setCommentCount(newPostList.get(0).getCommentCount());
                newPost.setUserNick(newPostList.get(0).getUserNick());
                newPost.setHitCount(newPostList.get(0).getHitCount());
                newPost.setLikeCount(newPostList.get(0).getLikeCount());
                if ("free".equalsIgnoreCase(newPost.getCategory())) {
                    newPost.setCategory("자유");
                } else if ("humor".equalsIgnoreCase(newPost.getCategory())) {
                    newPost.setCategory("유머");
                } else if ("photo".equalsIgnoreCase(newPost.getCategory())) {
                    newPost.setCategory("포토");
                }
                bestPostList.add(newPost);
            }
            return bestPostList;
        }

        return postList;
    }


}
