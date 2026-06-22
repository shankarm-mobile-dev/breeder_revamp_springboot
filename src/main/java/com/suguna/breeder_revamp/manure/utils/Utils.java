/*
 * Copyright (c) 2024 Created By Manibharathi R(1040025), Suguna Foods PVT Ltd; on 8/7/2024
 */
package com.suguna.breeder_revamp.manure.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utils {
    public static String generateOtp() {
       // return "123456";

        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static Date addMinutsToDate(int minutes) {
        // Create a Calendar instance
        Calendar calendar = Calendar.getInstance();

        // Set the date you want to modify
        calendar.setTime(new Date());

        // Add time (for example, adding 30 mins)
        calendar.add(Calendar.MINUTE, minutes);

        // Get the modified date

        return calendar.getTime();
    }

    public static int dateDifference(Date firstDate,Date secondDate)
    {
        long difference_In_Minutes = ((secondDate.getTime()-firstDate.getTime()) / (1000 * 60)) % 60;
        return (int)difference_In_Minutes;

        //eturn (int) ((secondDate.getTime()-firstDate.getTime())/1000);
    }
}
