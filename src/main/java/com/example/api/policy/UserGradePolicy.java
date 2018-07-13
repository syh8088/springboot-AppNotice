package com.example.api.policy;

import com.example.api.domain.UserGrade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 회원 계급 정책 Factory
 */
@Slf4j
@Component
public class UserGradePolicy {

    /**
     * 인원수 제한 없는 회원 계급 목록을 반환한다.
     */
    public List<UserGrade> createUnlimitedUserGradeList() {

        return unlimitedUserGradeListAfter5219();
    }

    /**
     *  사이트의 5219 정책 적용 후 인원수 제한 없는 회원 계급 목록을 반환한다.
     * <p>
     * http://ctf.bgstride.com/sf/go/artf5219
     *
     * @return 인원수 제한 없는 회원 계급 목록
     */
    private List<UserGrade> unlimitedUserGradeListAfter5219() {

        return new LinkedList<UserGrade>(Arrays.asList(

                UserGrade.builder().userGrade(0).description("입소전").requiredExp(0L).requiredPoint(0L).maxBlackList(0).maxDailySendMessageCount(0).maleImageUrl(namedDataRootUrl + gradePath + "/m0.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f0.png").build(),
                UserGrade.builder().userGrade(1).description("훈련병").requiredExp(1L).requiredPoint(0L).maxBlackList(50).maxDailySendMessageCount(0).maleImageUrl(namedDataRootUrl + gradePath + "/m1.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f1.png").build(),
                UserGrade.builder().userGrade(2).description("이등병").requiredExp(5000L).requiredPoint(0L).maxBlackList(60).maxDailySendMessageCount(10).maleImageUrl(namedDataRootUrl + gradePath + "/m2.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f2.png").build(),
                UserGrade.builder().userGrade(3).description("일병").requiredExp(100000L).requiredPoint(0L).maxBlackList(70).maxDailySendMessageCount(11).maleImageUrl(namedDataRootUrl + gradePath + "/m3.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f3.png").build(),
                UserGrade.builder().userGrade(4).description("상병").requiredExp(200000L).requiredPoint(0L).maxBlackList(80).maxDailySendMessageCount(12).maleImageUrl(namedDataRootUrl + gradePath + "/m4.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f4.png").build(),
                UserGrade.builder().userGrade(5).description("병장").requiredExp(400000L).requiredPoint(0L).maxBlackList(90).maxDailySendMessageCount(13).maleImageUrl(namedDataRootUrl + gradePath + "/m5.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f5.png").build(),
                UserGrade.builder().userGrade(6).description("하사").requiredExp(1140000L).requiredPoint(0L).maxBlackList(110).maxDailySendMessageCount(14).maleImageUrl(namedDataRootUrl + gradePath + "/m6.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f6.png").build(),
                UserGrade.builder().userGrade(7).description("중사").requiredExp(1425000L).requiredPoint(0L).maxBlackList(130).maxDailySendMessageCount(15).maleImageUrl(namedDataRootUrl + gradePath + "/m7.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f7.png").build(),
                UserGrade.builder().userGrade(8).description("상사").requiredExp(1710000L).requiredPoint(0L).maxBlackList(150).maxDailySendMessageCount(16).maleImageUrl(namedDataRootUrl + gradePath + "/m8.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f8.png").build(),
                UserGrade.builder().userGrade(9).description("원사").requiredExp(1995000L).requiredPoint(0L).maxBlackList(170).maxDailySendMessageCount(17).maleImageUrl(namedDataRootUrl + gradePath + "/m9.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f9.png").build(),
                UserGrade.builder().userGrade(10).description("소위").requiredExp(2280000L).requiredPoint(0L).maxBlackList(200).maxDailySendMessageCount(18).maleImageUrl(namedDataRootUrl + gradePath + "/m10.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f10.png").build(),
                UserGrade.builder().userGrade(11).description("중위").requiredExp(2660000L).requiredPoint(0L).maxBlackList(230).maxDailySendMessageCount(19).maleImageUrl(namedDataRootUrl + gradePath + "/m11.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f11.png").build(),
                UserGrade.builder().userGrade(12).description("대위").requiredExp(3040000L).requiredPoint(0L).maxBlackList(260).maxDailySendMessageCount(20).maleImageUrl(namedDataRootUrl + gradePath + "/m12.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f12.png").build(),
                UserGrade.builder().userGrade(13).description("소령(진)").requiredExp(3420000L).requiredPoint(0L).maxBlackList(310).maxDailySendMessageCount(21).maleImageUrl(namedDataRootUrl + gradePath + "/m13.png").femaleImageUrl(namedDataRootUrl + gradePath + "/f13.png").build()
        ));
    }



}
