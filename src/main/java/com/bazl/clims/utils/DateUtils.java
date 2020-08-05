package com.bazl.clims.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/7.
 */
public class DateUtils {

    public static final Date getCurrentDate() {
        return new Date();
    }

    public static final String YEAR_MM = "yyyyMM";
    public static final String YEAR = "yyyy";
    public static final String MONTH = "M";
    public static final String DAY = "d";
    public static final String HOUR = "H";
    public static final String MINUTES = "m";
    public static final String DATE = "yyyy-MM-dd";
    public static final String YEAR_MINUTES = "yyyy-MM-dd HH:mm";
    public static final String HOUR_MINUTES = "HH:mm";
    public static final String HOUR_MINUTES_C = "HH时mm分";
    public static final String MIN = "yyyy-MM-dd HH:mm:ss";
    public static final String HOUR_MINUTES_SECOND = "yyyyMMddHHmmss";
    /** 不自动补零 */
    public static final String DATE_SING = "yyyy-M-d";
    public static final String DATE_STR = "yyyy年MM月dd日";
    /** 不自动补零 */
    public static final String DATE_SING_STR = "yyyy年M月d日";

    public static final String dateToString(Date date, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(date);
    }

    public static final String getCurrentYearStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = sdf.format(getCurrentDate());
        return currentDateStr.substring(0,4);
    }

    public static final String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String currentDateStr = sdf.format(getCurrentDate());
        return currentDateStr.substring(0,4);
    }

    public static final String getCurrentMonthStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = sdf.format(getCurrentDate());
        return currentDateStr.substring(5,7);
    }

    public static final String getCurrentDayStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = sdf.format(getCurrentDate());
        return currentDateStr.substring(8,10);
    }

    public static Date stringToDate(String dateStr, String fmt) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            return sdf.parse(dateStr);
        }catch(Exception ex){
            return null;
        }
    }

    //给date 增加或减少  days/天
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtils.dateToString(new Date(),"yyyy年M月d日"));
    }
    public static Date addDate(int count) {
        try {
            Calendar calendar = Calendar.getInstance ();
            System.out.println (calendar.getTime());
            calendar.add (Calendar.SECOND, count);
            System.out.println (calendar.getTime());
            return calendar.getTime ();
        }catch(Exception ex){
            return null;
        }
    }
}
