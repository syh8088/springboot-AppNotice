package com.example.api.dto;

import com.example.api.entities.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GameTeamDto {
    private long id;
    private Team team;
    private String locationType;
    private LocalDateTime createDatetime;


}
