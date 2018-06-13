package com.example.api.service;

import com.example.api.config.converter.TimeConverter;
import com.example.api.dto.GameBaseDto;
import com.example.api.dto.GameTeamDto;
import com.example.api.entities.Game;
import com.example.api.exception.Ensure;
import com.example.api.exception.NamedException;
import com.example.api.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MedalPickService {

    @Autowired
    private GameRepository gameRepository;

    public void getGames(String startDateTime, long sportsId, Long[] exceptGameIds, String leagueName, String homeTeamName, String awayTeamName) throws Exception {

        Ensure.ifThenThrow(startDateTime == null,
                () -> new NamedException("StartDateTimeIsNullError", "날짜 데이타가 잘못되었습니다."));

        String exceptDashStartDateTime = startDateTime.replace("-", "");
        log.info("exceptDashStartDateTime.substring={}", exceptDashStartDateTime.substring(0, 8));
        log.info("exceptDashStartDateTime={},length={}", exceptDashStartDateTime, startDateTime.length());
        Ensure.ifThenThrow(exceptDashStartDateTime.length() != 17,
                () -> new NamedException("InvalidGameError", "날짜 형식이 잘못되었습니다."));

        if(exceptGameIds == null || exceptGameIds.length == 0) {
//            exceptGameIds = Lists.newArrayList(0L);
            exceptGameIds = new Long[1];
            exceptGameIds[0] = 0L;
        }


        boolean isLeagueNameQueried = StringUtils.isNotEmpty(leagueName);
        boolean isHomeTeamNameQueried = StringUtils.isNotEmpty(homeTeamName);
        boolean isAwayTeamNameQueried = StringUtils.isNotEmpty(awayTeamName);

        int queryCount = 0;
        if(isLeagueNameQueried) queryCount++;
        if(isHomeTeamNameQueried) queryCount++;
        if(isAwayTeamNameQueried) queryCount++;

        Ensure.ifThenThrow(queryCount > 1, () -> new NamedException("TooManySearchCondition", "검색 조건은 한가지만 허용됩니다."));

        //TODO:대상 경기 리스트를 가져온다.
        List<Game> games;

        games = gameRepository.findBySportsIdAndIdNotInAndStartDatetimeBetweenOrderByIdDesc(
                sportsId,
                exceptGameIds,
                TimeConverter.convertStartDateToLocalDateTime(exceptDashStartDateTime.substring(0, 8)),
                TimeConverter.convertEndDateToLocalDateTime(exceptDashStartDateTime.substring(0, 8)));




        //TODO:해당 경기의 GameTeam정보를 add한다.
        List<GameBaseDto<GameTeamDto>> gameBaseDtos = new ArrayList<>();


    }



}
