package com.example.api.entities;

import javax.persistence.Id;

public class League {
    @Id
    long id;
    long sportsId;
    String name;
    String abbreviation;
    String displayName;
    String color;
    String nation;

    public League(long id, long sportsId, String name, String abbreviation, String displayName, String color, String nation) {
        this.id = id;
        this.sportsId = sportsId;
        this.name = name;
        this.abbreviation = abbreviation;
        this.displayName = displayName;
        this.color = color;
        this.nation = nation;
    }

    public League(long id, long sportsId, String name, String abbreviation, String color) {
        this.id = id;
        this.sportsId = sportsId;
        this.name = name;

        this.abbreviation = abbreviation;
        this.color = color;
    }




}
