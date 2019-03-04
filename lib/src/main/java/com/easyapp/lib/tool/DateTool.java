package com.easyapp.lib.tool;

import java.util.Calendar;

public class DateTool {

    public static String getYM() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String value;

        if (month < 10) {
            value = year + "/0" + month;
        } else {
            value = year + "/" + month;
        }
        return value;
    }
}
