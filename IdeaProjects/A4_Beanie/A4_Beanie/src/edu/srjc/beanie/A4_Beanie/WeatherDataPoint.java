package edu.srjc.beanie.A4_Beanie;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class WeatherDataPoint
{
    private final int unixTimePos = 0;
    private final int outTempPos = 7;

    // i have a second data file that i've been testing with
    //    private final int unixTimePos = 1;
    //    private final int outTempPos = 8;

    private String date;
    private float temperature;

    // ***************************
    //      ctor
    // ***************************

    public WeatherDataPoint(String date, float temperature)
    {
        this.date = date;
        this.temperature = temperature;
    }

    public WeatherDataPoint()
    {
    }

    public WeatherDataPoint(String csvLine, String delimiter)
    {
        String[] fields = csvLine.split(delimiter);

        try
        {
            date = unixEpocToSimpleDate(fields[unixTimePos].replace("\"", ""));
        }
        catch (Exception e)
        {
            throw new InvalidParameterException("The date/time field was not found");
        }

        try
        {
            temperature = Float.parseFloat(fields[outTempPos].replace("\"", ""));
        }
        catch (NumberFormatException e)
        {
            throw new InvalidParameterException("The temperature field was invalid");
        }
    }

    // ***************************
    //      helpers
    // ***************************

    private String unixEpocToSimpleDate(String epocValue)
    {
        long epoc = Long.parseLong(epocValue);

        Date d = new Date(epoc * 1000);

        // https://stackoverflow.com/questions/11046053/how-to-format-date-string-in-java //https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html _

        date = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss").format(d);

        return date;
    }

    @Override
    public String toString()
    {
        return "WeatherDataPoint{" +
                "date='" + date + '\'' +
                ", temperature=" + String.format("%.2f", temperature) +
                '}';
    }

    // ***************************
    //     getters/setters
    // ***************************

    public String getDateTime()
    {
        return date;
    }


    public void setDateTime(String date)
    {
        this.date = date;
    }

    /**
     * returns the date part of the date
     * @return
     */
    public String getDate()
    {
        return date.split(" ")[0];
    }

    /**
     * returns the time part of the date
     * @return
     */
    public String getTime()
    {
        return date.split(" ")[1];
    }

    /**
     * returns the hours part of the time
     * @return
     */
    public String getHours()
    {
        return Time.getHours(getTime());
    }

    /**
     * returns the minutes part of the time
     * @return
     */
    public String getMinutes()
    {
        return Time.getMinutes(getTime());
    }

    public float getTemperature()
    {
        return temperature;
    }

    public void setTemperature(float temperature)
    {
        this.temperature = temperature;
    }
}
