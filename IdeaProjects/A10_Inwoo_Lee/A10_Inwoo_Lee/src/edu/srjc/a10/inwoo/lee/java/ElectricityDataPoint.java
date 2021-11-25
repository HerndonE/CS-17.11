package edu.srjc.a10.inwoo.lee.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ElectricityDataPoint extends ArrayList<HomeDataPoint>
{
    public ArrayList<String> dateArray = new ArrayList<>();
    public ArrayList<Double> valueArrayFirst = new ArrayList<>();
    public int electricityCounter = 0;
    DecimalFormat df = new DecimalFormat("#.00");

    ElectricityDataPoint(String csvFileName, String delimiter) throws FileNotFoundException
    {
        Scanner fIn = null;
        fIn = new Scanner(new File(csvFileName));

        fIn.nextLine();

        String line = fIn.nextLine().trim();
        String[] fields = line.split(delimiter);
        double electricUsage = 0;

        while (fIn.hasNextLine())
        {
            String tempDate = fields[1];
            dateArray.add(fields[1]);
            electricUsage = 0;

            while (tempDate.equals(fields[1]))
            {
                electricUsage += Double.parseDouble(fields[4]);

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
            electricUsage = Double.parseDouble(df.format(electricUsage));
            valueArrayFirst.add(electricUsage);
            ++electricityCounter;
        }
    }
}