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
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeDataPoint
{

    private String date;
    private Double usage;
    private Double gas;
    private Double highTemp;
    private Double lowTemp;

    public HomeDataPoint() {
    }

    public HomeDataPoint(String date, Double usage, Double gas, Double highTemp, Double lowTemp) {
        this.date = date;
        this.usage = usage;
        this.gas = gas;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }


    public Double getGas() {
        return gas;
    }

    public Double getHighTemp() {
        return highTemp;
    }

    public Double getLowTemp() {
        return lowTemp;
    }

    public String getDate() {
        return date;
    }

    public Double getUsage() {
        return usage;
    }

    @Override
    public String toString() {
        return "HomeDataPoint{" +
                "date='" + date + '\'' +
                ", usage=" + usage +
                ", gas=" + gas +
                ", highTemp=" + highTemp +
                ", lowTemp=" + lowTemp +
                '}';
    }



}
