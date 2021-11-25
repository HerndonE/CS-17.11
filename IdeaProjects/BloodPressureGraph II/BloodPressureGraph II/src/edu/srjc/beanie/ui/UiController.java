/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.srjc.beanie.ui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import edu.srjc.beanie.ui.model.BloodPressureReading;
import edu.srjc.beanie.ui.model.BpHistory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 *
 * @author 
 */
public class UiController implements Initializable
{
    // This is the master list of data

    BpHistory bp = null;

    @FXML
    MenuItem mnuFileOpen;
    @FXML
    MenuItem mnuFileExit;

    @FXML
    LineChart<String, Number> bpChart;

    @FXML
    CategoryAxis dateAxis = new CategoryAxis();

    @FXML
    NumberAxis valuesAxis = new NumberAxis();

    @FXML
    private void mnuFileOpen_Click()
    {

        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);

        if (f == null)
        {
            return;
        }

        try
        {
            bp = new BpHistory(f, ",");
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't open the file", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("FATAL ERROR");
            alert.showAndWait();

            Platform.exit();
        }
        initChart();
    }

    @FXML
    private void mnuFileExit_Click()
    {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }    

    private void initChart()
    {
        dateAxis.setLabel("Date");
        valuesAxis.setLabel("Reading");

        XYChart.Series systolicSeries = new XYChart.Series();
        systolicSeries.setName("Systolic");

        XYChart.Series diastolicSeries = new XYChart.Series();
        diastolicSeries.setName("Diastolic");

        XYChart.Series heartRateSeries = new XYChart.Series();
        heartRateSeries.setName("Heart Rate");

        for (BloodPressureReading bpr : bp)
        {
            XYChart.Data<String, Integer> sData = new XYChart.Data<>(bpr.getDate(), bpr.getSystolic());

            String s = String.format("Dt: %s\nS: %s\nD: %s\nHR: %s", bpr.getDate(), bpr.getSystolic(), bpr.getDiastolic(), bpr.getHeartRate());

            sData.setNode(new HoveredThresholdNode(s));
            systolicSeries.getData().addAll(sData);


            sData = new XYChart.Data<>(bpr.getDate(), bpr.getDiastolic());
            sData.setNode(new HoveredThresholdNode(s));
            diastolicSeries.getData().addAll(sData);

            sData = new XYChart.Data<>(bpr.getDate(), bpr.getHeartRate());
            sData.setNode(new HoveredThresholdNode(s));
            heartRateSeries.getData().addAll(sData);
        }

        bpChart.getData().add(systolicSeries);
        bpChart.getData().add(diastolicSeries);
        bpChart.getData().add(heartRateSeries);

    }

    class HoveredThresholdNode extends StackPane
    {
        public HoveredThresholdNode(String value)
        {
            setPrefSize(10,10);
            Label label = createBpDataLabel(value);

            setOnMouseEntered(new EventHandler<MouseEvent>()
              {
                  @Override
                  public void handle(MouseEvent event)
                  {
                      getChildren().setAll(label);
                      setCursor(Cursor.NONE);
                      toFront();
                  }
              }
            );
            setOnMouseExited(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    getChildren().clear();
                    setCursor(Cursor.CROSSHAIR);
                }
            });
        }

        private Label createBpDataLabel(String value)
        {
            Label label = new Label(value + "");
            label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
            label.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
            label.setTextFill(Color.FIREBRICK);
            label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
            return label;
        }
    }















}
