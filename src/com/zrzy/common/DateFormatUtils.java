package com.zrzy.common;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormatUtils {
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    public static String FORMAT2_SHORT = "yyyyMMdd";
    public static String FORMAT2_LONG = "yyyyMMddHHmmss";
    public static String FORMAT2_FULL = "yyyyMMddHHmmssS";

    public static String format(Date date, String pattern) {
        String returnValue = null;
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        returnValue = df.format(date);
        return returnValue;
    }
    
    public static String format(String pattern) {
        return format(null, pattern);
    }
    
    public static String format(Date date) {
        return format(date, FORMAT_LONG);
    }
    
    public static String format() {
        return format(null, FORMAT_LONG);
    }

    public static Date parse(String string) {
        return parse(string, FORMAT_LONG);
    }

    public static Date parse(String string, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}