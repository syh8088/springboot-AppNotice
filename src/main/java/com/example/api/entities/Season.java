package com.example.api.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "season")
@Data
@NoArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long sportsId;
    private String year;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('REGULAR','PRE','POST','PLAYOFF')")
    private Type type;
    private String name;

    @Column(name = "is_active")
    private boolean active;

    @ManyToOne(optional = false)
    @JoinColumn(name = "league_id", insertable = false, updatable = false)
    private League league;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season", fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<SeasonMap> seasonMapss = new ArrayList<>();

    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime createDatetime;

//    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "season")
//    private List<SeasonType> seasonTypes = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void createDatetime() {
        this.createDatetime = LocalDateTime.now();
    }

    public enum Type {
        REGULAR,PRE,POST,PLAYOFF;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", sportsId=" + sportsId +
                ", year='" + year + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", createDatetime=" + createDatetime +
                '}';
    }


}
