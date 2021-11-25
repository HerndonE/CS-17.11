/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
Ethan Herndon
EHerndon17@gmail.com
Date 5-24-17
Assignment #Final
CS 17.11
Media Player
*/
package edu.srjc.Ethan.Herndon.Media.Player;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));


        Scene scene = new Scene(root);
        stage.setTitle("Ethan Herndon Media Player Final");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

