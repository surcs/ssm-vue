package com.surc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {
    private static SimpleDateFormat simpleDateFormat;
    private static String format;

    static {
//        format = "yyyy-MM-dd-FF  HH:mm:ss";
        format = "yyyy-MM-dd";
        simpleDateFormat = new SimpleDateFormat(format,Locale.CHINA);
    }

    public static Date parse(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }
}
