package edu.srjc.beanie.A4_Beanie;

import java.security.InvalidParameterException;

/**
 * This file is Copyright 2017 by Sean R. Kirkpatrick (beanie@srjc.us). It may
 * not be reproduced, shared, or used in any way without the express written
 * permission of the author.
 * <p>
 * This file is written for CS 17.11, Java Programming at Santa Rosa Junior College.
 * If you are a student in one of the CS 17.11 classes that I teach, I give you
 * permission to use this within the context of that class, subject to the reproduction,
 * sharing, and use statement above.
 */
public class Time
{
    public static String getHours(String time)
    {
        assert(time != null);
        assert(time.contains(":"));
        String[] fields = time.split(":");
        return fields[0];
    }
    public static String getMinutes(String time)
    {
        assert(time != null);
        assert(time.contains(":"));
        String[] fields = time.split(":");
        return fields[1];
    }
}
