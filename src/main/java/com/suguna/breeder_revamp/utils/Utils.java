package com.suguna.breeder_revamp.utils;

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

    public static String Getnewotp() {
        int length = 6;
        String chars = "";
        String g_otp = "";
        chars = "1234567899865432113465895479854985646";
        Random random = new Random();
        char[] otp = new char[length];

        for (int i = 0; i < length; i++) {
            otp[i] = chars.charAt(random.nextInt(chars.length()));
            g_otp = g_otp + otp[i];
        }
        // g_otp="123456";
        return g_otp;
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

    public static Date MinutsToDate(int minutes) {
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
