package com.itworkorder.sqlgen.util;

import com.itworkorder.sqlgen.common.enums.DatePatternEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 线程安全、性能较好的Date工具类
 *
 * @author weidong.lin
 * @date 2019/11/8 16:23
 */
public class SafeDateUtil {

    private static ThreadLocal<DateFormat> dateFullFormatThreadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DatePatternEnum.YYYY_MM_DD_HH_MI_SS_S.getPattern());
        }
    };
    private static ThreadLocal<DateFormat> dateFormatThreadLocal1 = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DatePatternEnum.YYYY_MM_DD.getPattern());
        }
    };
    private static ThreadLocal<DateFormat> dateFormatThreadLocal2 = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DatePatternEnum.YYYY_MM_DD_HH_MM.getPattern());
        }
    };
    private static ThreadLocal<DateFormat> dateFormatThreadLocal3 = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DatePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern());
        }
    };
    private static ThreadLocal<DateFormat> dateFormatThreadLocal4 = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(DatePatternEnum.YYYYMMDD.getPattern());
        }
    };


    public static String format(Date date, DatePatternEnum datePatternEnum) {
        if (null == date || null == datePatternEnum) {
            return null;
        }
        return get(datePatternEnum).get().format(date);
    }

    public static String formatNextDay(Date date, DatePatternEnum datePatternEnum) {
        if (null == date || null == datePatternEnum) {
            return null;
        }
        setNextDay0H0M0S0MS(date);
        return get(datePatternEnum).get().format(date);
    }

    public static Date parse(String dateStr, DatePatternEnum datePatternEnum) throws ParseException {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        return get(datePatternEnum).get().parse(dateStr);
    }

    public static Date parse(Date date, DatePatternEnum datePatternEnum) throws ParseException {
        String dateStr = format(date, datePatternEnum);
        return parse(dateStr, datePatternEnum);
    }

    private static ThreadLocal<DateFormat> get(DatePatternEnum datePatternEnum) {
        switch (datePatternEnum) {
            case YYYY_MM_DD:
                return dateFormatThreadLocal1;
            case YYYY_MM_DD_HH_MM:
                return dateFormatThreadLocal2;
            case YYYY_MM_DD_HH_MM_SS:
                return dateFormatThreadLocal3;
            case YYYYMMDD:
                return dateFormatThreadLocal4;
            case YYYY_MM_DD_HH_MI_SS_S:
                return dateFullFormatThreadLocal;
            default:
                return dateFullFormatThreadLocal;
        }
    }

    /**
     * 时间设置到明天的0点0分0秒
     *
     * @param date
     */
    public static void setNextDay0H0M0S0MS(Date date) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
        date.setTime(calendar.getTimeInMillis());
    }

    /**
     * 获取Calendar 实例
     *
     * @return Calendar
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static Date parseDate(Object o) {
        if (o instanceof Date) {
            return (Date) o;
        } else if (o instanceof Number) {
            return new Date(((Number) o).longValue());
        } else if (o instanceof String) {
            try {
                Date date = DateUtils.parseDate((String) o, DatePatternEnum.YYYY_MM_DD_HH_MI_SS_S.getPattern(),
                        DatePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern(),
                        DatePatternEnum.YYYY_MM_DD.getPattern());
                return date;
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
