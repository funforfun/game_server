package utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeHelper {
    private final static String LANG = "ru";

    public static long getTimeInMs() {
        Date date = new Date();
        return date.getTime();
    }

    public static int getPOSIX() {
        Date date = new Date();
        int millisInSecond = 1000;
        return (int) (date.getTime() / millisInSecond);
    }

    public static String getUserDateFull(Locale locale) {
//        Locale locale1 = new Locale("ru");
        Date date = new Date();
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.FULL, locale);
        return dateFormatter.format(date);
    }

    public static String getUserDateFull() {
        return getUserDateFull(new Locale(LANG));
    }
}
