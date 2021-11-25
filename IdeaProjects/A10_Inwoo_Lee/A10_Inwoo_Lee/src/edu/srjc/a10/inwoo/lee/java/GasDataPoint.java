package edu.srjc.a10.inwoo.lee.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class GasDataPoint extends ArrayList<HomeDataPoint>
{
    public ArrayList<String> dateArray = new ArrayList<>();
    public ArrayList<Double> valueArrayFirst = new ArrayList<>();
    public int gasCounter = 0;
    DecimalFormat df = new DecimalFormat("0.00");

    GasDataPoint(String csvFileName, String delimiter) throws FileNotFoundException
    {
        Scanner fIn = null;
        fIn = new Scanner(new File(csvFileName));

        String line = fIn.nextLine().trim();
        String[] fields = line.split(delimiter);
        double gasUsage = 0;

        while (fIn.hasNextLine())
        {
            dateArray.add(fields[1]);
            gasUsage = Double.parseDouble(fields[2]);
            gasUsage = Double.parseDouble(df.format(gasUsage));
            valueArrayFirst.add(gasUsage);
            ++gasCounter;
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
}