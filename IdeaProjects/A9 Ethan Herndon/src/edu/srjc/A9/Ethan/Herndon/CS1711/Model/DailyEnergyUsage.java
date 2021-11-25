/*
Ethan Herndon
EHerndon17@gmail.com
4-12-17
Assignment #9
CS 17.11
Energy GUI program
*/

package edu.srjc.A9.Ethan.Herndon.CS1711.Model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class DailyEnergyUsage
{
     /*****************
      Private Members
     *****************/

    private StringProperty date = new SimpleStringProperty(this, "Date:", "");
    private StringProperty usage = new SimpleStringProperty(this, "Usage:", "");

     /*****************
      Constructor
     ****************/



    public DailyEnergyUsage(String date, String usage)
    {
        this.date.set(date);
        this.usage.set(usage);
    }


    /*****************
     Getters/Setters
     *****************/

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date.set(date);
    }

    public String getUsage()
    {
        return usage.get();
    }

    public StringProperty usageProperty()
    {
        return usage;
    }

    public void setUsage(String usage)
    {
        this.usage.set(usage);
    }





}
