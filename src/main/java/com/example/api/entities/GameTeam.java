package com.example.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class GameTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('HOME','AWAY')")
    private LocationType locationType;

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    @Column(name="create_datetime", updatable=false)
    private LocalDateTime createDatetime;

   /* @PrePersist
    private void prePersist() {
        this.setCreateDatetime(LocalDateTime.now());
    }*/

    @Override
    public String toString() {
        return "GameTeam{" +
                "id=" + id +
                ", game.getId()=" + game.getId() +
                ", team=" + team.toString() +
                ", locationType='" + locationType + '\'' +
//                ", scores=" + scores.size() +
//                ", specials=" + specials.size() +
                ", createDatetime=" + createDatetime +
                '}';
    }

    public enum LocationType {
        HOME,AWAY;
    }
}
