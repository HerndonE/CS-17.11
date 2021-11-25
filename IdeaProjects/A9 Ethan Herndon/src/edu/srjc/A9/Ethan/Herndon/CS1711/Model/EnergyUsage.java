/*
Ethan Herndon
EHerndon17@gmail.com
4-12-17
Assignment #9
CS 17.11
Energy GUI program
*/

package edu.srjc.A9.Ethan.Herndon.CS1711.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class EnergyUsage extends ArrayList<DailyEnergyUsage>
{
    // this line was taken from
    // https://www.mkyong.com/java/java-display-double-in-2-decimal-points/
    private static DecimalFormat decimalFormat2 = new DecimalFormat(".##");

    public EnergyUsage(String energyCsvFile, String delimiter) throws FileNotFoundException
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
                usageCount += Float.parseFloat(fields[4]);

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

            this.add(new DailyEnergyUsage("Date: " + lastdate,"Usage: " + decimalFormat2.format(usageCount)));
        }
        }



}
