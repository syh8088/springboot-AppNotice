package com.example.api.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 회원 계급 정보 Domain
 *
 * @author
 */
@Data // FIXME @DATA 는 사용을 지양합니다.
@Builder
public class UserGrade {

    // 번호
    private Integer userGrade;

    // 설명
    private String description;

    // 최소 요구 포인트
    private Long requiredPoint;

    // 최소 요구 경험치
    private Long requiredExp;

    // 인원수 제한
    private Integer limitedUserCount;

    // 허용 최대 블랙리스트 수
    private Integer maxBlackList;

    // 하루 발송 가능한 쪽지 수
    private Integer maxDailySendMessageCount;

    // 계급 이미지 Url
    private String femaleImageUrl;

    private String maleImageUrl;
}