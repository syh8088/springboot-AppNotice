package com.example.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sports_id")
    private long sportsId;

    @OneToOne
    @JoinColumn(name = "league_id")
    private League league;

    @OneToOne
    @JoinColumn(name = "season_id")
    private Season season;

    private String displayTime;

    private int period;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime startDatetime;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime realStartDatetime;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('READY', 'IN_PROGRESS', 'FINAL', 'CANCEL', 'DELAY')")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('READY', 'IN_PROGRESS', 'TOP', 'BOTTOM', 'SHOTOUT', 'HALFTIME')")
    private ProgressStatus progressStatus;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('WIN', 'LOSE', 'DRAW', 'CANCEL', 'UNKNOWN')")
    private Result result;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('MANUAL', 'AUTO', 'UNSAVE')")
    private ParsingType parsingType;

    private int parsing_source_type;

    @Column(name = "broadcast_link")
    private String broadcastLink;

    private boolean isRegisterLineup;
    private boolean isBoardShow;

    private int infozoneNewsCount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game", fetch = FetchType.EAGER)
    private List<GameTeam> gameTeams;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime createDatetime;

    @PrePersist
    public void createDatetime() {
        this.createDatetime = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return "Game{" +
                "id=" + id +
                ", sportsId=" + sportsId +
                ", league=" + league +
                ", season=" + season +
                ", displayTime=" + displayTime +
                ", startDatetime=" + startDatetime +
//                ", realStartDatetime=" + realStartDatetime +
                ", status=" + status +
                "}";
    }

    public enum Status {
        READY,IN_PROGRESS,FINAL,CANCEL,DELAY;
    }

    public enum ProgressStatus {
        READY,IN_PROGRESS,TOP,BOTTOM,SHOTOUT,HALFTIME;
    }

    public enum Result {
        WIN,LOSE,DRAW,CANCEL,UNKNOWN;
    }

    public enum ParsingType {
        MANUAL, AUTO, UNSAVE;
    }

}
