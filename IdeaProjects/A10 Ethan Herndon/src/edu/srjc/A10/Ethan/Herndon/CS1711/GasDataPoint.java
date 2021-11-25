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
import java.util.ArrayList;
import java.util.Scanner;


public class GasDataPoint  extends ArrayList<HomeDataPoint> {


    public GasDataPoint(String energyCsvFile, String delimiter) throws FileNotFoundException
    {
        Scanner fIn = null;

        fIn = new Scanner(new File(energyCsvFile));
        fIn.nextLine();

        String date;
        float usage;
        float usageCount =0;

        String line = fIn.nextLine().trim();
        String[] fields = line.split(",");

        while (fIn.hasNextLine())
        {
            String lastdate = fields[1];
            usageCount = 0;
            while(lastdate.equals(fields[1]))
            {
                usageCount += Float.parseFloat(fields[2]);

                if(fIn.hasNextLine())
                {
                    line = fIn.nextLine().trim();
                    fields = line.split(",");
                }
                else
                {
                    break;
                }
            }

            this.add(new HomeDataPoint( lastdate,null,Double.parseDouble(String.format("%.2f",usageCount)),null,null));
        }
    }

}
