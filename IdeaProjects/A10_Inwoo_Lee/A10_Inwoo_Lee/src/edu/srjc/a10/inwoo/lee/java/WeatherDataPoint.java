package edu.srjc.a10.inwoo.lee.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class WeatherDataPoint extends ArrayList<HomeDataPoint>
{

    private final int unixTimePos = 0;
    private final int outTempPos = 7;
    public ArrayList<String> dateArray = new ArrayList<>();
    public ArrayList<Double> valueArrayFirst = new ArrayList<>();
    public ArrayList<Double> valueArraySecond = new ArrayList<>();
    public int weatherCounter = 0;
    public String weatherDate;
    private Double highTemp;
    private Double lowTemp;
    DecimalFormat df = new DecimalFormat("0.00");

    WeatherDataPoint()
    {

    }

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
            dateArray.add(tempDate);

            highTemp = Double.parseDouble(fields[outTempPos].replace("\"", ""));
            lowTemp = Double.parseDouble(fields[outTempPos].replace("\"", ""));

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
            highTemp = Double.parseDouble(df.format(highTemp));
            lowTemp = Double.parseDouble(df.format(lowTemp));
            valueArrayFirst.add(highTemp);
            valueArraySecond.add(lowTemp);
            ++weatherCounter;

            //System.out.println("date is " + getWeatherDate());
            //System.out.println("hightemps is "+ highTemp + doubleHigh);
            //System.out.println("lowtemps is "+ lowTemp + doubleLow);
            //System.out.println("weatherCounter is at " + weatherCounter);
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