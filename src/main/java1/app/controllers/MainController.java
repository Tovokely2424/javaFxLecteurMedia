package main.java1.app.controllers;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MainController {
    @FXML private Button playButton;
    @FXML private Button pauseButton;
    @FXML private Button stopButton;
    @FXML private ProgressBar progressBarMusic;
    @FXML private Label currentTimeLabel;
    @FXML private Label totalTimeLabel;

    private MediaPlayer mediaPlayer;
    @FXML
    public void initialize(){
        playButton.setOnAction(e->playMusic());
        pauseButton.setOnAction(e->pauseMusic());
        stopButton.setOnAction(e->stopMusic());
    }

    private void playMusic(){
        if(mediaPlayer == null){
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier Audio", "*.mp3"));
            File file = fileChooser.showOpenDialog(null);
            if(file != null){
                Media media =  new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                //ProgressBar
                mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime)->updateUILecteur(newTime));
                mediaPlayer.setOnReady(()->{
                    Duration duration = mediaPlayer.getTotalDuration();
                    totalTimeLabel.setText(formatTime(duration));
                });
            }
        }
        if (mediaPlayer != null){
            mediaPlayer.play();

        }
    }

    private String formatTime(Duration duration) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format((long)duration.toMillis());
    }

    private void pauseMusic(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    private void stopMusic(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
            progressBarMusic.setProgress(0);
            currentTimeLabel.setText("00:00");
        }
    }

    private void updateUILecteur(Duration currentTime){
        if (mediaPlayer != null){
            double progress =  currentTime.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
            Platform.runLater(()->{
                progressBarMusic.setProgress(progress);
                currentTimeLabel.setText(formatTime(currentTime));
            });
        }
    }
}
