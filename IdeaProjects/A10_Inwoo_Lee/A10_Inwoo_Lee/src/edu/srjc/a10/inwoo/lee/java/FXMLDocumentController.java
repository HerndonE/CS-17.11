package edu.srjc.a10.inwoo.lee.java;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable
{
    public final String electricityFile = System.getProperty("user.dir") + "/elec.csv";
    public final String gasFile = System.getProperty("user.dir") + "/gas.csv";
    public final String weatherFile = System.getProperty("user.dir") + "/KCASONOM43.csv";
    @FXML
    LineChart<String, Number> lineChart;
    @FXML
    CategoryAxis dateAxis = new CategoryAxis();
    @FXML
    NumberAxis valuesAxis = new NumberAxis();
    @FXML
    private void allClick(ActionEvent event)
    {
        allButton();
    }
    @FXML
    private void janClick(ActionEvent event)
    {
        janButton();
    }
    @FXML
    private void febClick(ActionEvent event)
    {
        febButton();
    }


    private ElectricityDataPoint electricityDataPoint;
    private GasDataPoint gasDataPoint;
    private WeatherDataPoint weatherDataPoint;
    private HomeDataPoint homeDataPoint;

    ArrayList<String> finalDateArray = new ArrayList<>();
    ArrayList<Double> finalGasArray = new ArrayList<>();
    ArrayList<Double> finalElectricityArray = new ArrayList<>();
    ArrayList<Double> finalTempLowArray = new ArrayList<>();
    ArrayList<Double> finalTempHighArray = new ArrayList<>();

    public void initialize(URL url, ResourceBundle rb) {

        try {
            electricityDataPoint = new ElectricityDataPoint(electricityFile, ",");
            gasDataPoint = new GasDataPoint(gasFile, ",");
            weatherDataPoint = new WeatherDataPoint(weatherFile, ",");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot open the file", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();

            Platform.exit();
        }

        try {
            homeDataPoint = new HomeDataPoint(electricityFile, gasFile, weatherFile, ",");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Value validation has failed", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();

            Platform.exit();
        }
        ArrayList<String> dateArray = new ArrayList<>();
        ArrayList<Double> gasArray = new ArrayList<>();
        ArrayList<Double> electricityArray = new ArrayList<>();
        int counter = 0;

        for (int i = 0; i < gasDataPoint.gasCounter; ++i) {
            for (int j = 0; j < electricityDataPoint.electricityCounter; ++j) {
                if (gasDataPoint.dateArray.get(i).equals(electricityDataPoint.dateArray.get(j))) {
                    dateArray.add(gasDataPoint.dateArray.get(i));
                    gasArray.add(gasDataPoint.valueArrayFirst.get(i));
                    electricityArray.add(electricityDataPoint.valueArrayFirst.get(j));
                    ++counter;
                }
            }
        }

        for (int k = 0; k < counter; ++k) {
            for (int l = 0; l < weatherDataPoint.weatherCounter; ++l) {
                if (dateArray.get(k).equals(weatherDataPoint.dateArray.get(l))) {
                    finalDateArray.add(dateArray.get(k));
                    finalGasArray.add(gasArray.get(k));
                    finalElectricityArray.add(electricityArray.get(k));
                    finalTempHighArray.add(weatherDataPoint.valueArrayFirst.get(l));
                    finalTempLowArray.add(weatherDataPoint.valueArraySecond.get(l));
                }
            }
        }
    }
    private void allButton()
        {
            lineChart.getData().clear();
            dateAxis.setLabel("Date");
            valuesAxis.setLabel("Reading");

            XYChart.Series electricSeries = new XYChart.Series();
            electricSeries.setName("Electric");

            XYChart.Series gasSeries = new XYChart.Series();
            gasSeries.setName("Gas");

            XYChart.Series highTempSeries = new XYChart.Series();
            highTempSeries.setName("Maximum Temperature");

            XYChart.Series lowTempSeries = new XYChart.Series();
            lowTempSeries.setName("Minimum Temperature");

            for(int i = 0; i < finalDateArray.size(); ++i)
            {
                electricSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalElectricityArray.get(i)));
                gasSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalGasArray.get(i)));
                highTempSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalTempHighArray.get(i)));
                lowTempSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalTempLowArray.get(i)));

            }

            lineChart.getData().add(electricSeries);
            lineChart.getData().add(gasSeries);
            lineChart.getData().add(highTempSeries);
            lineChart.getData().add(lowTempSeries);
        }

    private void janButton()
    {
        lineChart.getData().clear();

        dateAxis.setLabel("Date");
        valuesAxis.setLabel("Reading");

        XYChart.Series electricSeries = new XYChart.Series();
        electricSeries.setName("Electric");

        XYChart.Series gasSeries = new XYChart.Series();
        gasSeries.setName("Gas");

        XYChart.Series highTempSeries = new XYChart.Series();
        highTempSeries.setName("Maximum Temperature");

        XYChart.Series lowTempSeries = new XYChart.Series();
        lowTempSeries.setName("Minimum Temperature");


        int counter = 0;
        String month;
        for(int i=0; i<finalDateArray.size(); ++i)
        {
            month = finalDateArray.get(i).split("-")[1];
            if(month.equals("01"))
            {
                ++counter;
            }
        }
        for(int i = 0; i < counter; ++i)
        {
            electricSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalElectricityArray.get(i)));
            gasSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalGasArray.get(i)));
            highTempSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalTempHighArray.get(i)));
            lowTempSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalTempLowArray.get(i)));

        }

        lineChart.getData().add(electricSeries);
        lineChart.getData().add(gasSeries);
        lineChart.getData().add(highTempSeries);
        lineChart.getData().add(lowTempSeries);

        lineChart.getData().removeAll();
    }

    private void febButton()
    {
        lineChart.getData().clear();

        dateAxis.setLabel("Date");
        valuesAxis.setLabel("Reading");

        XYChart.Series electricSeries = new XYChart.Series();
        electricSeries.setName("Electric");

        XYChart.Series gasSeries = new XYChart.Series();
        gasSeries.setName("Gas");

        XYChart.Series highTempSeries = new XYChart.Series();
        highTempSeries.setName("Maximum Temperature");

        XYChart.Series lowTempSeries = new XYChart.Series();
        lowTempSeries.setName("Minimum Temperature");

        int counter = 0;
        String month;
        for(int i=0; i<finalDateArray.size(); ++i)
        {
            month = finalDateArray.get(i).split("-")[1];
            if(month.equals("01"))
            {
                ++counter;
            }
        }

        for(int i = counter; i < finalDateArray.size(); ++i)
        {
            electricSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalElectricityArray.get(i)));
            gasSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalGasArray.get(i)));
            highTempSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalTempHighArray.get(i)));
            lowTempSeries.getData().add(new XYChart.Data<String, Number>(finalDateArray.get(i), finalTempLowArray.get(i)));

        }

        lineChart.getData().add(electricSeries);
        lineChart.getData().add(gasSeries);
        lineChart.getData().add(highTempSeries);
        lineChart.getData().add(lowTempSeries);
    }
}