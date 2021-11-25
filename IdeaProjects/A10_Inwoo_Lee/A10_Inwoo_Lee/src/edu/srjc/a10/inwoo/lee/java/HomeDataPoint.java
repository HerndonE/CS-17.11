package edu.srjc.a10.inwoo.lee.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class HomeDataPoint
{
    WeatherDataPoint wdp = new WeatherDataPoint();
    private String dataPointDate;
    private Double electricValue;
    private Double gasValue;
    private Double weatherLow;
    private Double weatherHigh;

    public HomeDataPoint(String elecFileName, String gasFileName, String weatherFileName, String delimiter) throws FileNotFoundException
    {
        checkElec(elecFileName, delimiter);
        checkGas(gasFileName, delimiter);
        checkWeather(weatherFileName, delimiter);
    }

    public void add(int a, String inDate, Double inElectric, Double inGas, Double inHighWeather, Double inLowWeather)
    {
        this.dataPointDate = inDate;
        this.electricValue = inElectric;
        this.gasValue = inGas;
        this.weatherLow = inLowWeather;
        this.weatherHigh = inHighWeather;
    }

    private void checkElec(String elecFileName, String delimiter) throws FileNotFoundException
    {
        Scanner fInE = null;
        fInE = new Scanner(new File(elecFileName));

        fInE.nextLine();

        String elecLines = fInE.nextLine().trim();
        String[] eFields = elecLines.split(delimiter);
        while (fInE.hasNextLine())
        {
            validation(eFields[1], eFields[4]);

            if (fInE.hasNextLine())
            {
                elecLines = fInE.nextLine().trim();
                eFields = elecLines.split(delimiter);
            }
            else
            {
                break;
            }
        }
    }

    private void checkGas(String gasFileName, String delimiter) throws FileNotFoundException
    {
        Scanner fInG = null;
        fInG = new Scanner(new File(gasFileName));

        String gasLines = fInG.nextLine().trim();
        String[] gFields = gasLines.split(delimiter);
        while (fInG.hasNextLine())
        {
            validation(gFields[1], gFields[2]);

            if (fInG.hasNextLine())
            {
                gasLines = fInG.nextLine().trim();
                gFields = gasLines.split(delimiter);
            }
            else
            {
                break;
            }
        }

    }

    private void checkWeather(String weatherFileName, String delimiter) throws FileNotFoundException
    {
        Scanner fIn = null;
        fIn = new Scanner(new File(weatherFileName));

        fIn.nextLine();
        fIn.nextLine();
        fIn.nextLine();
        String line = fIn.nextLine().trim();
        String[] fields = line.split(delimiter);
        while (fIn.hasNextLine())
        {
            String tempDate = wdp.unixEpocToSimpleDate(fields[0].replace("\"", ""));
            String tempTemp = fields[7].replace("\"", "");
            validation(tempDate, tempTemp);

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
    }

    private void validation(String date, String utilityValue)
    {
        try
        {
            String dateYear;
            String dateMonth;
            String dateDay;

            dateYear = date.split("-")[0];
            dateMonth = date.split("-")[1];
            dateDay = date.split("-")[2];

            Integer.parseInt(dateYear);
            Integer.parseInt(dateMonth);
            Integer.parseInt(dateDay);

        } catch (Exception e)
        {
            throw new InvalidParameterException("Invalid date value!");
        }

        try
        {
            Double.parseDouble(utilityValue);
        } catch (Exception e)
        {
            throw new InvalidParameterException("Invalid utility value!");
        }
    }

    public String getdataPointDate()
    {
        return dataPointDate;
    }

    public Double getElectricValue()
    {
        return electricValue;
    }

    public Double getGasValue()
    {
        return gasValue;
    }

    public Double getWeatherLow()
    {
        return weatherLow;
    }

    public Double getWeatherHigh()
    {
        return weatherHigh;
    }

}