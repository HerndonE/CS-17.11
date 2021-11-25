/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
Ethan Herndon
EHerndon17@gmail.com
4-12-17
Assignment #9
CS 17.11
Energy GUI program
*/
package edu.srjc.A9.Ethan.Herndon.CS1711;

import java.net.URL;
import java.util.ResourceBundle;

import edu.srjc.A9.Ethan.Herndon.CS1711.Model.EnergyUsage;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

/**
 *
 * @author 
 */

public class UiController implements Initializable
{

    private EnergyUsage energy;

    private IntegerProperty idx = new SimpleIntegerProperty(this, "idx", -1);

    @FXML
    TableView tblData;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            energy = new EnergyUsage(System.getProperty("user.dir") + "/pge_electric_hourly_usage_data.csv", ",");
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot open the file", ButtonType.OK);
            alert.setHeaderText(null);
            alert.show();

            Platform.exit();
        }

        tblData.setItems(FXCollections.observableArrayList(energy));
    }
}
