package com.example.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class GameBaseDto<T> {
    private long id;
    private String displayTime;
    private int period;
    private LocalDateTime startDatetime;
    private LocalDateTime realStartDatetime;
    private String status;
    private String progressStatus;
    private String result;
    private String parsingType;
    private int parsingSourceType;
    private String broadcastLink;
    private Boolean isRegisterLineup;
    private Boolean isBoardShow;
    private int infozoneNewsCount;
    private LeagueDto league;
    private List<GameBroadcastDto> broadcasts;
    private List<GameOddsDto> odds;
    private List<T> gameTeams;
}
