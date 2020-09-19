package com.bosssoft.ecds.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author syf
 * @Date 2020/8/24 22:00
 */
public class DateUtils {
    private static Date getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        return calendar.getTime();
    }
    public static ArrayList<Date> getDays(int intervals) {
        ArrayList<Date> pastDaysList = new ArrayList<>();
        for (int i = intervals -1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }
}
