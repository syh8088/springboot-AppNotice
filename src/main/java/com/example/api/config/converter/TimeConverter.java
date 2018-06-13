package com.example.api.config.converter;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class TimeConverter {
    /*
     * 현재날짜를 기준으로 해당 날짜 검색을 위해 00:00:00으로 시작시간을 LocalDateTime으로 리턴한다.
     */
    public static LocalDateTime convertNowStartDateToLocalDateTime(){
        //두자리와 한자리를 모두 수용하기 위한 포멧
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s");
        LocalDate nowDate = LocalDate.now();
        String dateTimeString = nowDate.toString() + " " + "00:00:00";
        LocalDateTime nowStartdateTime = LocalDateTime.parse(dateTimeString, formatter);
        return nowStartdateTime;
    }

    /*
     * 입력날짜를 기준으로 해당 날짜 검색을 위해 00:00:00으로 시작시간을 LocalDateTime으로 리턴한다.
     */
    public static LocalDateTime convertStartDateToLocalDateTime(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd H:m:s");
        String dateTimeString = dateString + " " + "00:00:00";
        LocalDateTime nowStartdateTime = LocalDateTime.parse(dateTimeString, formatter);
        return nowStartdateTime;
    }

    /*
     * 현재날짜를 기준으로 해당 날짜 검색을 위해 23:59:59으로 끝시간을 LocalDateTime으로 리턴한다.
     */
    public static LocalDateTime convertNowEndDateToLocalDateTime(){
        //두자리와 한자리를 모두 수용하기 위한 포멧
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s");
        LocalDate nowDate = LocalDate.now();
        String dateTimeString = nowDate.toString() + " " + "23:59:59";
        LocalDateTime nowEndDateTime = LocalDateTime.parse(dateTimeString, formatter);
        return nowEndDateTime;
    }

    /*
     * 입력날짜를 기준으로 해당 날짜 검색을 위해 23:59:59으로 끝시간을 LocalDateTime으로 리턴한다.
     */
    public static LocalDateTime convertEndDateToLocalDateTime(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd H:m:s");
        String dateTimeString = dateString + " " + "23:59:59";
        LocalDateTime nowEndDateTime = LocalDateTime.parse(dateTimeString, formatter);
        return nowEndDateTime;
    }

    public static LocalDateTime convertDateTimeStringToLocalDateTime(String dateTimeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd H:m:s");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public static Date convertStringDateToDate(String strDate) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(strDate);
    }

    public static Time convertStringTimeToTime(String strTime) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = format.parse(strTime);
        return new Time(date.getTime());
    }

    public static String nowDateString(){
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return localDate.format(formatter);
    }

    public static boolean compareCurrentDateFromLocalDateTime(LocalDateTime fromDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime nowDateTime = LocalDateTime.now();

        String strFromDateTime = fromDateTime.format(formatter);
        String strNowDateTime = nowDateTime.format(formatter);

        return strNowDateTime.equals(strFromDateTime);
    }



}
