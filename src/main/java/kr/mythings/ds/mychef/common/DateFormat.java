package kr.mythings.ds.mychef.common;

import java.time.format.DateTimeFormatter;

public class DateFormat {

    static final String DATE_TIME_FORMAT_STRING_FULL = "yyyy-MM-dd HH:mm:ss";
    static final String DATE_TIME_FORMAT_STRING_YMD = "yyyy-MM-dd";
    static final String DATE_TIME_FORMAT_STRING_HMS = "HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER_FULL = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STRING_FULL);
    public static final DateTimeFormatter DATE_TIME_FORMATTER_YMD = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STRING_YMD);
    public static final DateTimeFormatter DATE_TIME_FORMATTER_HMS = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STRING_HMS);



}
