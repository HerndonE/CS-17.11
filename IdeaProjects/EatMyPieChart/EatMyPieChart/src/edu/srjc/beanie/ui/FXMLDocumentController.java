/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.beanie.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author 
 */
public class  FXMLDocumentController implements Initializable
{
    
   ObservableList<PieChart.Data> windData = FXCollections.observableArrayList(
        new PieChart.Data("Trumpets", 2),
           new PieChart.Data("Horns", 4),
           new PieChart.Data("Trombones", 3),
           new PieChart.Data("Tubas", 1),
           new PieChart.Data("Flutes", 2),
           new PieChart.Data("Piccolo", 1),
           new PieChart.Data("Oboes", 2),
           new PieChart.Data("Clarinets", 2),
           new PieChart.Data("Bassoons", 2)
           );

   @FXML
   PieChart myPieChart;

   @FXML
   Label lblCaption;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        myPieChart.setData(windData);
        myPieChart.setLabelsVisible(true);
        setChartEventHandlers();
    }    

    private void setChartEventHandlers()
    {
        for (PieChart.Data data  : myPieChart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>()
                    {
                        @Override
                        public void handle(MouseEvent event)
                        {
                            lblCaption.setTextFill(Color.BLACK);
                            //lblCaption.setStyle("-fx-font: 24 arial bold;");
                            lblCaption.setStyle("-fx-font: 24 arial bold;");
                            lblCaption.setTranslateX(event.getSceneX());
                            lblCaption.setTranslateY(event.getSceneY());
                            lblCaption.setText(String.valueOf(data.getPieValue()));
                        }
                    });
        }
    }





















}
