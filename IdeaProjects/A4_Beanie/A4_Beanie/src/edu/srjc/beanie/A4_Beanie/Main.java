package edu.srjc.beanie.A4_Beanie;

import java.io.FileNotFoundException;
import java.util.Scanner;

/*
This file is Copyright 2017 by Sean R. Kirkpatrick (beanie@srjc.us). It may
not be reproduced, shared, or used in any way without the express written
permission of the author.

This file is written for CS 17.11, Java Programming at Santa Rosa Junior College.
If you are a student in one of the CS 17.11 classes that I teach, I give you
permission to use this within the context of that class, subject to the reproduction,
sharing, and use statement above.
*/
public class Main
{
    public static void main(String[] args)
    {
        /*
        i created a list class called WeatherDataList. it
        has a ctor that takes the name of a file. it attempts
        to parse that file and add the points that it finds
         */
        WeatherDataList allPointsList = null;

        Scanner kbd = new Scanner(System.in);
        System.out.print("Filename? ");
        String fname = kbd.nextLine();
        kbd.close();
        kbd = null;

        String fileName = String.format("%s/%s", System.getProperty("user.dir"), fname);
        try
        {
            allPointsList = new WeatherDataList(fileName);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        
        /*
        this is debug code that helped me develop the WeatherDataList
        class. all it does is display the points that the list contains
         */
        //for (WeatherDataPoint wdp: allPointsList)
        //{
        //    System.out.println(String.format("This point is %s", wdp));
        //}

        if (allPointsList.size() > 0)
        {
            WeatherDataList todaysTempsList = new WeatherDataList();

            // we have at least 1 point. it becomes the first target
            String targetDate = allPointsList.get(0).getDate();
            String targetHour = allPointsList.get(0).getHours();

            float temperatureAccumulator = 0f;

            int tempsFound = 0;

            for (int i = 0; i < allPointsList.size(); i++) {
                WeatherDataPoint wdp = allPointsList.get(i);

                // matches the date we're interested in
                if (wdp.getDate().equals(targetDate))
                {
                    // matches the time (hour) we're interested in
                    if (wdp.getHours().equals(targetHour))
                    {
                        temperatureAccumulator += wdp.getTemperature();
                        tempsFound += 1;
                    }
                    else
                    {
                        // new hour, add it to the new list.
                        todaysTempsList.add(new WeatherDataPoint(wdp.getDateTime(), temperatureAccumulator / tempsFound));
                        tempsFound = 1;
                        targetDate = wdp.getDate();
                        targetHour = wdp.getHours();
                        temperatureAccumulator = wdp.getTemperature();
                    }
                }
                else
                {
                    // new date
                    tempsFound = 1;
                    targetDate = wdp.getDate();
                    targetHour = wdp.getTime();
                    temperatureAccumulator = wdp.getTemperature();
                }
            }

//            for (WeatherDataPoint wdp : todaysTempsList)
//            {
//                System.out.println(wdp.toString());
//            }

            // when we exit this loop, we should have a new list of
            // unique days with averaged
            if (todaysTempsList.size() > 0)
            {
                // this will be the coldest point (hour) of any day
                WeatherDataPoint coldest = todaysTempsList.get(0);
                targetDate = todaysTempsList.get(0).getDate();

                for (WeatherDataPoint thisPoint: todaysTempsList)
                {
                    // if it's the same day, we've potentially got 24 hours
                    // find the coldest
                    if (thisPoint.getDate().equals(targetDate))
                    {
                        float f = thisPoint.getTemperature();


                        if (thisPoint.getTemperature() < coldest.getTemperature())
                        {
                            coldest = thisPoint;
                        }
                    }
                    else
                    {
                        // new day, output the coldest hour
                        System.out.println(String.format("The coldest hour for %s is %s, temp was %.2f", coldest.getDate(), coldest.getTime(), coldest.getTemperature()));
                        targetDate = thisPoint.getDate();
                        coldest = thisPoint;
                    }
                }
            }
        }
        else
        {
            System.out.println("No data points to process.");
        }
    }
}
