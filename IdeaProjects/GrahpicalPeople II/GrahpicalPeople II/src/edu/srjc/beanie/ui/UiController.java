/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.beanie.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.srjc.beanie.ui.model.People;
import edu.srjc.beanie.ui.model.Person;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 *
 * @author 
 */
public class UiController implements Initializable
{
    private People people;

    private IntegerProperty idx = new SimpleIntegerProperty(this, "idx", -1);

    @FXML
    TableView tblPeople;


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            people = new People(System.getProperty("user.dir") + "/100 dummy names.csv", "|");
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot open the file", ButtonType.OK);
            alert.setHeaderText(null);
            alert.show();

            Platform.exit();
        }

        tblPeople.setItems(FXCollections.observableArrayList(people));
    }
    
}
