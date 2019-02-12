package com.example.yanjiang.stockchart.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by houwentao on 2017/5/25.
 */

public class TimeUtils {
    private static String DATE_FORMAT = "yyyy-MM-dd";
    private static String TIME_FORMAT = "HH:mm:ss";

    /**
     * 计算相差天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int daysBetween(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(startDate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(endDate));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 判断time是否在from，to之内
     *
     * @param time 指定日期
     * @param from 开始日期
     * @param to   结束日期
     * @return
     * @throws ParseException
     */
    public static boolean belongCalendar(String time, String from, String to) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        try {
            Date time2 = sdf.parse(time);
            Date from2 = sdf.parse(from);
            Date to2 = sdf.parse(to);

            Calendar date = Calendar.getInstance();
            date.setTime(time2);

            Calendar after = Calendar.getInstance();
            after.setTime(from2);

            Calendar before = Calendar.getInstance();
            before.setTime(to2);

            if (date.after(after) && date.before(before)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
