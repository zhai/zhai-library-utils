package com.zhaisoft.lib.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhai on 12/18/15.
 */
public class DateTimeUtil {

    public static final String DEFAULTDATEFORMAT = "yyyy-MM-dd  HH:mm:ss";

    /**
     * 得到当前时间
     *
     * @param format
     * @return
     */
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    /**
     * 得到当前时间
     *
     * @return yyyy-MM-dd  HH:mm:ss
     */
    public static String getCurrentTime() {
        return getCurrentTime(DEFAULTDATEFORMAT);
    }


}
