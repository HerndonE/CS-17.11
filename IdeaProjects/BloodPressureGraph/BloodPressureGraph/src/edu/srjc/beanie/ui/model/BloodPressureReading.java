package edu.srjc.beanie.ui.model;

import java.security.InvalidParameterException;

/**
 * Created by seank on 4/10/17.
 */

public class BloodPressureReading
{
    @Override
    public String toString()
    {
        return "BloodPressureReading{" +
                "\n\tdate = '" + date + '\'' +
                ",\n\tsystolic=" + systolic +
                ",\n\tdiastolic=" + diastolic +
                ",\n\theartRate=" + heartRate +
                "\n" + '}';
    }

    // date/time the reading was taken
    private String date;

    // the top number - heart contracting
    private int systolic;

    // the second number - heart relaxing
    private int diastolic;

    // beatsPerMinute
    private int heartRate;

    public BloodPressureReading(String values, String delimiter)
    {
        if (values.length() == 0)
        {
            throw new InvalidParameterException("Invalid number of values!");
        }
        String[] fields = values.split("[" + delimiter + "]");
        if (fields.length != 4)
        {
            throw new InvalidParameterException("Invalid number of values!");
        }
        setValues(fields[0], fields[1], fields[2], fields[3]);
    }

    public String getDate()
    {
        return date;
    }

    private void setValues(String dateTime, String syst, String dias, String hr)
    {
        try
        {
            date = dateTime.split(" ")[0];
        }
        catch (Exception e)
        {
            throw new InvalidParameterException("Invalid date/time value!");
        }

        try
        {
            systolic = Integer.parseInt(syst);
            if (systolic <= 0)
            {
                throw new InvalidParameterException("Invalid systolic value!");
            }
        }
        catch (Exception e)
        {
            throw new InvalidParameterException("Invalid systolic value!");
        }

        try
        {
            diastolic = Integer.parseInt(dias);
            if (systolic <= 0)
            {
                throw new InvalidParameterException("Invalid diastolic value!");
            }
        }
        catch (Exception e)
        {
            throw new InvalidParameterException("Invalid diastolic value!");
        }

        try
        {
            heartRate = Integer.parseInt(hr);
            if (heartRate <= 0)
            {
                throw new InvalidParameterException("Invalid heart rate value!");
            }
        }
        catch (Exception e)
        {
            throw new InvalidParameterException("Invalid heart rate value!");
        }

    }

    public int getSystolic()
    {
        return systolic;
    }

    public int getDiastolic()
    {
        return diastolic;
    }

    public int getHeartRate()
    {
        return heartRate;
    }
}
