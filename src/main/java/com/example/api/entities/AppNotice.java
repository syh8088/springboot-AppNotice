package com.example.api.entities;

import com.example.api.config.hibernate.LocalDateTimeAttributeConverter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Slf4j
@Data
public class AppNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "enum('NOTICE', 'EVENT', 'UPDATE')")
    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    @Lob
    private String content;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime reserveAt;

    private long viewCount;

    public enum Category {
        NOTICE("NOTICE"), EVENT("EVENT"), UPDATE("UPDATE");

        private String value;

        Category(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }







}
