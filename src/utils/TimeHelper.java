package utils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeHelper {

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
        Date date = new Date();
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.FULL, locale);
        return dateFormatter.format(date);
    }
}
