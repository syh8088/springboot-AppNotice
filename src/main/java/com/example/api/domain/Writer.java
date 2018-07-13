package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * Components 사용자정보
 * JsonView를 통한 User의 json filter가 수월하지 않은 환경이어서 새롭게 Domain을 구성하여
 * 각 component domain의 중복코드를 제거한다.
 * 데이타베이스를 사용하지 않는 member variable 에 대한 처리가 logic 에 추가되어야 한다.
 * TODO User Domain 정리이후에 상속을 받아 사용할수 있도록 변경
 * <p>
 * Created by on 2016-12-07.
 */
@NoArgsConstructor
@Data
public class Writer {

    private String id;
    private String nick;
    private String todayWord;
    // search from file - required additional processing
    private boolean hasProfileImage;
    private String profileImageUrl;
    private String profileImagePath;
    private int grade;
    private String gradeDescription;
    private boolean isAdmin;
    private boolean isSuperAdmin;
    private boolean isMale;
    private boolean isFemale;
    private boolean isPrisoner;
    // 전과회수
    private int convictCount;
    private boolean isHacked;
    private boolean isBanned;
    private boolean isCanceled;
    private boolean isNickRed;
    private boolean isNickBlue;
    private boolean isNickPink;
    private LocalDateTime registrationDateTime;
    private LocalDateTime lastChatLoginDateTime;
    // need for grade calculating
    @JsonIgnore
    private Long point;
    @JsonIgnore
    private Long exp;
    @JsonIgnore
    private Long gp;

    public boolean isHasProfileImage() {
        return StringUtils.isNotEmpty(this.profileImagePath);
    }

//    public String getProfileImageUrl() {
//        if (StringUtils.isNotEmpty(profileImagePath)) {
//            return PropertyProvider.getByNamedDataRootUrl() + profileImagePath;
//        }
//        return null;
//    }
}
