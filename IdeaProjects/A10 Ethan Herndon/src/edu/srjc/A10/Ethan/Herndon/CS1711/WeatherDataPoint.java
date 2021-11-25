/*
Ethan Herndon
EHerndon17@gmail.com
4/19/17
Assignment #10
CS 17.11
GUI temp,usage,high/low temp
*/
package edu.srjc.A10.Ethan.Herndon.CS1711;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by EHern on 4/19/2017.
 */
public class WeatherDataPoint extends ArrayList<HomeDataPoint>
{
    private final int unixTimePos = 0;
    private final int outTempPos = 7;
     public ArrayList<String> dateArray = new ArrayList<>();
    public int weatherCounter = 0;
    public String weatherDate;
    private Double highTemp;
    private Double lowTemp;
    DecimalFormat d2f = new DecimalFormat("0.00");



    WeatherDataPoint(String csvFileName, String delimiter) throws FileNotFoundException
    {
        Scanner fIn = null;
        fIn = new Scanner(new File(csvFileName));

        fIn.nextLine();
        fIn.nextLine();
        fIn.nextLine();
        String line = fIn.nextLine().trim();
        String[] fields = line.split(delimiter);
        while (fIn.hasNextLine())
        {

            String tempDate = unixEpocToSimpleDate(fields[unixTimePos].replace("\"", ""));
            highTemp = Double.parseDouble(fields[outTempPos].replace("\"", ""));
            lowTemp = Double.parseDouble(fields[outTempPos].replace("\"", ""));
            dateArray.add(tempDate);
            while (tempDate.equals(unixEpocToSimpleDate(fields[unixTimePos].replace("\"", ""))))
            {

                if (Double.parseDouble(fields[outTempPos].replace("\"", "")) < lowTemp)
                {
                    lowTemp = Double.parseDouble(fields[outTempPos].replace("\"", ""));
                }

                if (Double.parseDouble(fields[outTempPos].replace("\"", "")) > highTemp)
                {
                    highTemp = Double.parseDouble(fields[outTempPos].replace("\"", ""));
                }

                if (fIn.hasNextLine())
                {
                    line = fIn.nextLine().trim();
                    fields = line.split(delimiter);
                }
                else
                {
                    break;
                }
            }
            highTemp = Double.parseDouble(d2f.format(highTemp));
            lowTemp = Double.parseDouble(d2f.format(lowTemp));
            ++weatherCounter;
            this.add(new HomeDataPoint(tempDate,null,null,highTemp,lowTemp));
        }
    }

    public String getWeatherDate()
    {
        return weatherDate;
    }

    public void setWeatherDate(String weatherDate)
    {
        this.weatherDate = weatherDate;
    }

    public String unixEpocToSimpleDate(String epocValue)
    {
        long epoc = Long.parseLong(epocValue);

        Date d = new Date(epoc * 1000);

        // https://stackoverflow.com/questions/11046053/how-to-format-date-string-in-java //https://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html _

        weatherDate = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(d);
        weatherDate = weatherDate.substring(0, weatherDate.length() - 9);
        return weatherDate;
    }
}
