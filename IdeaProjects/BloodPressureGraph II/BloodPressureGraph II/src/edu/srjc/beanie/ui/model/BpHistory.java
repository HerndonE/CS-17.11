package edu.srjc.beanie.ui.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by seank on 4/10/17.
 */
public class BpHistory extends ArrayList<BloodPressureReading>
{
    public BpHistory(String fileName, String delimiter) throws FileNotFoundException
    {
        File histFile = new File(fileName);
        loadHistory(histFile, delimiter);

    }
    public BpHistory(File histFile, String delimiter) throws FileNotFoundException
    {
        loadHistory(histFile, delimiter);
    }

    private void loadHistory (File historyFile, String delimiter) throws FileNotFoundException
    {
        Scanner fIn = new Scanner(historyFile);

        int cnt = 0;
        String line = "";
        while (fIn.hasNextLine())
        {
            line = fIn.nextLine().trim();
            cnt += 1;
            if (cnt % 3 != 0)
            {
                continue;
            }
            if (line.length() == 0 || line.startsWith("#"))
            {
                continue;
            }

            try
            {
                this.add(new BloodPressureReading(line, delimiter));
            }
            catch (Exception e)
            {
                System.out.println(String.format("ERROR: %s, IGNORING THIS READING", e.getMessage()));
            }
        }
        Collections.reverse(this);
    }
}
