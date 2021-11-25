/*
Ethan Herndon
EHerndon17@gmail.com
4/19/17
Assignment #10
CS 17.11
GUI temp,usage,high/low temp
*/
package edu.srjc.A10.Ethan.Herndon.CS1711;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author 
 */
public class UIController implements Initializable
{

   private ElectricDataPoint edp;
   private GasDataPoint gdp;
   private WeatherDataPoint wdp;

    ArrayList<String> SameMonth = new ArrayList<>();

   public final String electricFile = System.getProperty("user.dir") + "/elec.csv";
   public final String gasFile  = System.getProperty("user.dir") + "/gas.csv";
   public final String weatherFile1 = System.getProperty("user.dir") + "/KCASONOM43.csv";


    @FXML
    CategoryAxis dateAxis = new CategoryAxis();

    @FXML
    NumberAxis valuesAxis = new NumberAxis();
    @FXML
    LineChart<String, Number> bpChart = new LineChart<String, Number>(dateAxis,valuesAxis);


    @FXML
    public void janClick(ActionEvent event)
    {
        janButton();
    }

    @FXML
    public void febClick(ActionEvent event)
    {
        febButton();
    }


    public void janButton()
    {

        bpChart.getData().clear();
        dateAxis.setLabel("Date");
        valuesAxis.setLabel("Usage");
        valuesAxis.setLabel("Gas");
        valuesAxis.setLabel("High Temp");
        valuesAxis.setLabel("Low Temp");

        XYChart.Series usageSeries = new XYChart.Series();
        usageSeries.setName("Usage");

        XYChart.Series gasSeries = new XYChart.Series();
        gasSeries.setName("Gas");

        XYChart.Series lowSeries = new XYChart.Series();
        lowSeries.setName("Low Temp");

        XYChart.Series highSeries = new XYChart.Series();
        highSeries.setName("High Temp");

        int counter = 0;
        String month;
        for(int i = 0; i< SameMonth.size(); ++i)
        {

            month = SameMonth.get(i).split("-")[1];
            if(month.equals("01"))
            {
                ++counter;
            }

        }

            for (HomeDataPoint hdp : edp) {
                XYChart.Data<String, Number> sData = new XYChart.Data<String, Number>(hdp.getDate(), hdp.getUsage());
                usageSeries.getData().addAll(sData);
                String s = String.format("Date: %s\nUsage: %s",hdp.getDate(),hdp.getUsage().toString() );
                sData.setNode(new HoveredThresholdNode(s));
            }



            for (HomeDataPoint hdp : gdp) {
                XYChart.Data<String, Number> sData = new XYChart.Data<String, Number>(hdp.getDate(), hdp.getGas());
                gasSeries.getData().addAll(sData);
                String s = String.format("Date: %s\nGas: %s",hdp.getDate(),hdp.getGas().toString() );
                sData.setNode(new HoveredThresholdNode(s));
            }


            for (HomeDataPoint hdp : wdp) {
                XYChart.Data<String, Number> sData = new XYChart.Data<String, Number>(hdp.getDate(), hdp.getLowTemp());
                XYChart.Data<String, Number> lData = new XYChart.Data<String, Number>(hdp.getDate(), hdp.getHighTemp());
                lowSeries.getData().addAll(sData);
                highSeries.getData().addAll(lData);
                String s = String.format("Date: %s\nHigh: %s in blue\nLow: %s in green\n",hdp.getDate(),hdp.getHighTemp().toString(),hdp.getLowTemp().toString() );
                sData.setNode(new HoveredThresholdNode(s));

            }



        bpChart.getData().add(usageSeries);
        bpChart.getData().add(gasSeries);
        bpChart.getData().add(lowSeries);
        bpChart.getData().add(highSeries);


    }
    private void febButton()
    {
        bpChart.getData().clear();
        dateAxis.setLabel("Date");
        valuesAxis.setLabel("Usage");
        valuesAxis.setLabel("Gas");
        valuesAxis.setLabel("High Temp");
        valuesAxis.setLabel("Low Temp");

        XYChart.Series usageSeries = new XYChart.Series();
        usageSeries.setName("Usage");

        XYChart.Series gasSeries = new XYChart.Series();
        gasSeries.setName("Gas");

        XYChart.Series lowSeries = new XYChart.Series();
        lowSeries.setName("Low Temp");

        XYChart.Series highSeries = new XYChart.Series();
        highSeries.setName("High Temp");

        int counter = 0;
        String month;
        for(int i = 0; i< SameMonth.size(); ++i)
        {
            month = SameMonth.get(i).split("-")[1];
            if(month.equals("02"))
            {
                ++counter;
            }
        }

    for (HomeDataPoint hdp : edp) {
        XYChart.Data<String, Number> sData = new XYChart.Data<String, Number>(hdp.getDate(), hdp.getUsage());
        usageSeries.getData().addAll(sData);
        String s = String.format("Date: %s\nUsage: %s",hdp.getDate(),hdp.getUsage().toString() );
        sData.setNode(new HoveredThresholdNode(s));

    }


    for (HomeDataPoint hdp : gdp) {
        XYChart.Data<String, Number> sData = new XYChart.Data<String, Number>(hdp.getDate(), hdp.getGas());
        gasSeries.getData().addAll(sData);
        String s = String.format("Date: %s\nGas: %s",hdp.getDate(),hdp.getGas().toString() );
        sData.setNode(new HoveredThresholdNode(s));
    }


    for (HomeDataPoint hdp : wdp) {
        XYChart.Data<String, Number> sData = new XYChart.Data<String, Number>(hdp.getDate(), hdp.getLowTemp());
        XYChart.Data<String, Number> lData = new XYChart.Data<String, Number>(hdp.getDate(), hdp.getHighTemp());
        lowSeries.getData().addAll(sData);
        highSeries.getData().addAll(lData);
        String s = String.format("Date: %s\nHigh: %s\nLow: %s\n",hdp.getDate(),hdp.getHighTemp().toString(),hdp.getLowTemp().toString() );
        sData.setNode(new HoveredThresholdNode(s));

    }


        bpChart.getData().add(usageSeries);
        bpChart.getData().add(gasSeries);
        bpChart.getData().add(lowSeries);
        bpChart.getData().add(highSeries);


    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
             edp = new ElectricDataPoint(electricFile, ",");
             gdp = new GasDataPoint(gasFile,",");
             wdp = new WeatherDataPoint(weatherFile1,",");
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't open the file", ButtonType.OK);
            alert.setHeaderText(null);
            alert.setTitle("FATAL ERROR");
            alert.showAndWait();

            Platform.exit();
        }


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
