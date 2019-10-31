/*
 * Cai.xin Inc.
 * Copyright (c) 2016-2019 All Rights Reserved.
 */

package com.jjson.common.util.helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Objects;

/**
 * 日期工具类，使用Java8推荐类，LocalDateTime和Instance
 *
 * @author 幽明
 * @serial 2019-08-05
 */
public class DateUtils {

    /**
     * 默认的时间格式
     */
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认的日期格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 日期格式 YYYYMMDD
     */
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 格式化日期为"yyyy-MM-dd hh:mm:ss"字符串
     *
     * @param date 日期
     * @return "yyyy-MM-dd hh:mm:ss"
     */
    public static String format(Date date) {
        return formatToString(date, DEFAULT_DATE_TIME_PATTERN);
    }

    /**
     * 格式化日期为"yyyy-MM-dd hh:mm:ss"字符串
     *
     * @param date    日期
     * @param pattern 输出格式
     * @return "yyyy-MM-dd hh:mm:ss"
     */
    public static String format(Date date, String pattern) {
        return formatToString(date, pattern);
    }

    /**
     * 将"yyyy-MM-dd"字符串转为日期
     *
     * @param dateStr 日期字符串
     * @return an instance of java.util.Date
     */
    public static Date valueOf(String dateStr) {
        return valueOf(dateStr, DEFAULT_DATE_PATTERN);
    }

    /**
     * 将字符串转为日期，格式为pattern
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return an instance of java.util.Date
     */
    public static Date valueOf(String dateStr, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(dateStr, dtf);
        ZonedDateTime zdt = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * java.time.LocalDate --> java.util.Date
     * 把locateDate转为date
     *
     * @return date类型日期
     */
    public static Date LocalDateToUdate() {
        LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 比较两个日期年、月、日是否相同
     *
     * @param first  第一个日期
     * @param second 第二个日期
     * @return true如果两个日期的年月日相同，否则false
     */
    public static boolean compareDate(Date first, Date second) {
        if (Objects.isNull(first) && Objects.isNull(second)) {
            return true;
        }

        if (Objects.isNull(first) || Objects.isNull(second)) {
            return false;
        }

        if (first == second) {
            return true;
        }

        Instant a = first.toInstant();
        Instant b = second.toInstant();
        return a.get(ChronoField.YEAR) == b.get(ChronoField.YEAR)
                && a.get(ChronoField.DAY_OF_YEAR) == b.get(ChronoField.DAY_OF_YEAR);
    }

    private static String formatToString(Date date, String pattern) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dtf);
    }
}
