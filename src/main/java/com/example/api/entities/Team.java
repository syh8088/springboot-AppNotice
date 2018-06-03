package com.example.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "team")
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    private long sportsId;
    @JsonIgnore
    private long leagueId;
    private String name;
    private String nameEn;
    private String location;
    private String abbreviation;
    private String nickname;
    private String imgPath;
    private String imgPathApp;
    private String imgPathPc;

    public Team() {
    }

    public Team(long sportsId, String name) {
        this.sportsId = sportsId;
        this.name = name;
    }

    public Team(long sportsId, String location, String nickname, String abbreviation) {
        this.sportsId = sportsId;
        this.location = location;
        this.nickname = nickname;
        this.abbreviation = abbreviation;
    }

    public Team(long sportsId, String name, String nameEn, String location, String abbreviation, String nickname, String imgPath) {
        this.sportsId = sportsId;
        this.name = name;
        this.nameEn = nameEn;
        this.location = location;
        this.abbreviation = abbreviation;
        this.nickname = nickname;
        this.imgPath = imgPath;
    }
}
