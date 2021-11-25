package edu.srjc.beanie.ui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
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
public class People extends ArrayList<Person>
{
    public People(String peopleCsvFile, String delimiter) throws FileNotFoundException
    {
        Scanner fIn = null;

        fIn = new Scanner(new File(peopleCsvFile));
        String line;
        while (fIn.hasNext())
        {
            line = fIn.nextLine();
            String[] fields = line.split("[" + delimiter + "]");
            this.add(new Person(fields[0], fields[1], fields[2], fields[3], fields[4]));
        }
    }
}
