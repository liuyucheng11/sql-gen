package com.itworkorder.sqlgen.common.enums;

/**
 * 日期格式枚举
 *
 * @date 2019/3/8
 */
public enum DatePatternEnum {

    /**
     * 日期格式 yyyy-MM-dd
     */
    YYYY_MM_DD("yyyy-MM-dd"),

    /**
     * 日期格式 yyyyMMdd
     */
    YYYYMMDD("yyyyMMdd"),
    /**
     * 日期格式 yyyy-MM-dd hh:mm:ss
     */
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

    /**
     * 日期格式 yyyy-MM-dd hh:mm
     */
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),

    /**
     * 日期格式 yyyy-MM-dd HH:mm:ss.SSS
     */
    YYYY_MM_DD_HH_MI_SS_S("yyyy-MM-dd HH:mm:ss.SSS");

    private final String pattern;

    DatePatternEnum(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
