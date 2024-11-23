package ijse.edu.main.samagicinnamonmerchant.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HomePage {

    @FXML
    private Label lblDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    // Create a Timeline to update the time every second
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                // Get the current time and format it
                String currentTime = LocalTime.now().format(formatter);
                // Set the formatted time to the label
                lblDate.setText(currentTime);
            })
    );

}
