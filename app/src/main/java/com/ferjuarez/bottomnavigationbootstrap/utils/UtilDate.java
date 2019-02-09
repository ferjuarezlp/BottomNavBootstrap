package com.ferjuarez.bottomnavigationbootstrap.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

public class UtilDate {
    private static final String DATE_FORMAT_TIME = "dd/MM HH:mm";
    private static final String DATE_FORMAT_SIMPLE = "dd.MM.yyyy";
    private static final String DATE_FORMAT_NAMING = "yyyyMMdd";
    private static final String DATE_FORMAT_TIME_24 = "HH:mm";
    private static final String PATTERN_FULL_DATE = "EEE, d MMM yyyy HH:mm";
    private static final String PATTERN_MONTH_DATE = "EEEE dd 'de' MMMM";
    private static final String FORMAT_TIME = "HH:mm";

    @Inject
    public UtilDate() {
    }

    public Date getDate() {
        return new Date();
    }

    public String formatToFullDate(Date date) {
        String value = createDateFormat(PATTERN_FULL_DATE).format(date);
        value = value.substring(0, 1).toUpperCase() + value.substring(1);
        return value;
    }

    public String getFormattedStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TIME_24, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(date);
    }

    String formatToMonthAndTime(Date date) {
        DateFormat dateFormat = createDateFormat(DATE_FORMAT_TIME);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    String formatTime(Date date) {
        DateFormat dateFormat = createDateFormat(FORMAT_TIME);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);
    }

    public String formatToMonth(Date date) {
        String value = createDateFormat(PATTERN_MONTH_DATE).format(date);
        return value.toUpperCase();
    }

    public String formatToSimpleDate(Date date) {
        String value = createDateFormat(DATE_FORMAT_SIMPLE).format(date);
        return value.toUpperCase();
    }

    public String formatToNamingDate(Date date) {
        String value = createDateFormat(DATE_FORMAT_NAMING).format(date);
        return value.toUpperCase();
    }

    private DateFormat createDateFormat(String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault());
    }

    /**
     * Static methods
     */
    public static Date parseTimeToDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000L);
        return calendar.getTime();
    }

    public static Date parseHour(String hour) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        try {
            return sdf.parse(hour);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(date);
    }

    public static int getParsedHour(String hourAndMinutes){
        return Integer.valueOf(hourAndMinutes.split(":")[0]);
    }

    public static int getParsedMinute(String hourAndMinutes){
        return Integer.valueOf(hourAndMinutes.split(":")[1]);
    }

    public static boolean isValidateHourAndMinutes(int hourFrom, int minuteFrom, int choosedHours, int choosedMinutes){
        if(choosedHours > hourFrom){
            return true;
        } else if(choosedHours == hourFrom){
            if(choosedMinutes > minuteFrom){
                return true;
            }
        }
        return false;
    }

    public static long createDateWith(int hour, int minutes){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar.getTime().getTime() / 1000L;
    }

    public static String formatHourAndMinutes(int hour, int minutes){
        String formattedHour = String.valueOf(hour);
        String formattedMinutes = String.valueOf(minutes);
        if(hour <= 9) {
            formattedHour = "0" + hour;
        }
        if(minutes <= 9) {
            formattedMinutes = "0" + minutes;
        }
        return formattedHour + ":" + formattedMinutes + " hs";
    }


}