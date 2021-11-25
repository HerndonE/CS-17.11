package edu.srjc.beanie.ui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
public class Person
{
    private IntegerProperty id = new SimpleIntegerProperty("this", "id", -1);
    private StringProperty name = new SimpleStringProperty(this, "name", "");
    private StringProperty address = new SimpleStringProperty(this, "address", "");
    private StringProperty city = new SimpleStringProperty(this, "city", "");
    private StringProperty state = new SimpleStringProperty(this, "state", "");
    private StringProperty zip = new SimpleStringProperty(this, "zip", "");

    // ***************************
    //     CTOR
    // ***************************


    public Person(String name, String address, String city, String state, String zip)
    {
        this.id.set(this.hashCode());
        this.name.set(name);
        this.address.set(address);
        this.city.set(city);
        this.state.set(state);
        this.zip.set(zip);
    }

    public Person()
    {
    }

    // ***************************
    //     getters/setters
    // ***************************
    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public String getAddress()
    {
        return address.get();
    }

    public StringProperty addressProperty()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address.set(address);
    }

    public String getCity()
    {
        return city.get();
    }

    public StringProperty cityProperty()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city.set(city);
    }

    public String getState()
    {
        return state.get();
    }

    public StringProperty stateProperty()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state.set(state);
    }

    public String getZip()
    {
        return zip.get();
    }

    public StringProperty zipProperty()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip.set(zip);
    }
}
