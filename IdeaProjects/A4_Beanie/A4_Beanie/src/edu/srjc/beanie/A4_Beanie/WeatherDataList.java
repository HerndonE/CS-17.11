package edu.srjc.beanie.A4_Beanie;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
public class WeatherDataList extends ArrayList<WeatherDataPoint>
{
    private String weatherDataFileName;

    // ***************************
    //      ctor
    // ***************************

    /**
     * create an empty list for adding points
     * one by one
     */
    public WeatherDataList()
    {
    }

    /**
     * this constructor takes the name of a data file to process.
     * if found, the fileName will be opened and the contents parsed.
     * @param fileName
     */
    public WeatherDataList(String fileName) throws FileNotFoundException
    {
        File dataFile = new File(fileName);
        Scanner inFile = null;

        try
        {
            inFile = new Scanner(dataFile);
        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException("The specified file cannot be found");
        }

        String line = "";
        while (inFile.hasNextLine())
        {
            line = inFile.nextLine();
            if (line.trim().length() == 0 || line.trim().startsWith("#"))
            {
                continue;
            }
            try
            {
                // this class inherits from ArrayList, that means that
                // when we create the WeatherDataPoint object in main
                // it ultimately invokes the CTOR in the ArrayList
                // class. we can invoke the super.add method here because
                // 'super' refers to the base class where the add method
                // is defined.
                super.add(new WeatherDataPoint(line, ","));
            }
            catch (Exception e)
            {
                // there's a possibility the add won't succeed for whatever
                // reason. just ignore that point
            }

        }
        inFile.close();
        inFile = null;
    }
}
