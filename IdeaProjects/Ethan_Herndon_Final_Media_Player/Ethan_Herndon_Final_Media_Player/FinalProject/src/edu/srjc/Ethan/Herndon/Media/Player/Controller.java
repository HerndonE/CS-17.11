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

// As a reference
//https://docs.oracle.com/javafx/2/media/simpleplayer.htm

package edu.srjc.Ethan.Herndon.Media.Player;

import java.io.File;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;


public class Controller implements Initializable {

    @FXML
    MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;

    @FXML
    private Button skipBackward;

    @FXML
    private Button play;

    @FXML
    private Button pause;

    @FXML
    private Button skipForward;

    @FXML
    private Button repeat;

    @FXML
    private Slider adjustSound;

    @FXML
    private Label volumeDisplay;

    @FXML
    private Label showTime;

    @FXML
    private Label nameOfMedia;

    @FXML
    private Slider mediaSeek;

    @FXML
    private Label currentState;

    private static DecimalFormat decimalFormat2 = new DecimalFormat(" ");

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        play.setDisable(true);
        pause.setDisable(true);
        skipForward.setDisable(true);
        skipBackward.setDisable(true);
        repeat.setDisable(true);
    }

    @FXML
    private void playButton(ActionEvent event)
    {
        mediaPlayer.play();
        mediaPlayer.setRate(1);
        currentState.setText("Playing");
    }

    @FXML
    private void pauseButton(ActionEvent event)
    {
        mediaPlayer.pause();
        currentState.setText("Paused");
    }

    @FXML
    private void skipForward(ActionEvent event)
    {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(20)));
        currentState.setText(null);
    }

    @FXML
    private void skipBackward(ActionEvent event)
    {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(20)));
        currentState.setText(null);
    }

    @FXML
    private void reloadButton(ActionEvent event)
    {
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.play();
        mediaPlayer.setRate(1);
        currentState.setText(null);
    }

    @FXML
    private void mediaChoose(ActionEvent event)
    {
        play.setDisable(false);
        pause.setDisable(false);
        skipForward.setDisable(false);
        skipBackward.setDisable(false);
        repeat.setDisable(false);

        FileChooser fc = new FileChooser();

        //if you uncomment line 154, comment line 155 if you want to put a file location of your media
        //fc.setInitialDirectory(new File("******YOUR PATH DIRECTORY TO MEDIA HERE*********"));
         Window primaryStage = null;
        fc.getExtensionFilters().addAll(
                (new FileChooser.ExtensionFilter("Media Files", "*mp3", "*mp4",
                        "*.wav", "*.aac", "*.flv", "*.vob", "*.avi")));

        File f = fc.showOpenDialog(null);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        media = new Media(new File(String.valueOf(f)).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        adjustSound.setValue(mediaPlayer.getVolume() * 100);
        volumeDisplay.setText(decimalFormat2.format(mediaPlayer.getVolume() * 100));
        nameOfMedia.setText(f.toString());

        adjustSound.valueProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {

                mediaPlayer.setVolume(adjustSound.getValue() / 100);
                volumeDisplay.setText(decimalFormat2.format(mediaPlayer.getVolume() * 100));

            }

        });


        mediaPlayer.currentTimeProperty().addListener(new InvalidationListener()
        {
            public void invalidated(Observable observable)
            {
                showTime.setText(formatTime(mediaPlayer.getCurrentTime(), media.getDuration()));
            }
        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>()
        {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration previous, Duration current)
            {
                double changeTime = current.toSeconds()/mediaPlayer.getStopTime().toSeconds();
                if(!mediaSeek.isPressed())
                {
                    mediaSeek.setValue(changeTime*100);
                }
            }
        });
        mediaSeek.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                if(mediaPlayer != null)
                {
                    mediaPlayer.seek(Duration.seconds(mediaPlayer.getStopTime().toSeconds()*(mediaSeek.getValue()/100)));
                }
            }
        });

        nameOfMedia.setText(f.getName());

        //Looked around at a few YouTube tutorials
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"Width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"Height"));

    }


    //https://docs.oracle.com/javase/8/javafx/media-tutorial/playercontrol.htm
    private static String formatTime(Duration elapsed, Duration duration)
    {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }



}
