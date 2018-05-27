package com.example.api.entities;


import com.example.api.config.hibernate.LocalDateTimeAttributeConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Slf4j
@Data
public class AppNoticeDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long noticeId;

    @Column(columnDefinition = "enum('MOBILE_WEB','SPORT_ANDROID','SPORT_IOS','GAME_ANDROID','GAME_IOS')")
    @Enumerated(EnumType.STRING)
    private Type type;

    private boolean noticeTopAllowed;

    private boolean popupAllowed;

    private String popupImagePath;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime popupStartDate;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime popupEndDate;

    @ManyToOne
    @JoinColumn(name = "noticeId", insertable = false, updatable = false)
    private AppNotice appNotice;

    public enum Type {
        MOBILE_WEB("MOBILE_WEB"), SPORT_ANDROID("SPORT_ANDROID"), SPORT_IOS("SPORT_IOS"), GAME_ANDROID("GAME_ANDROID"), GAME_IOS("GAME_IOS");

        String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
