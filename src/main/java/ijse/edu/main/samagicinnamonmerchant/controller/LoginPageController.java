package ijse.edu.main.samagicinnamonmerchant.controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;

public class LoginPageController {
    private final String correctUsername = "chamindu";
    private final String correctPassword = "1234";

    @FXML
    private Button btnLogIn;
    @FXML
    private Hyperlink hyperPassword;

    @FXML
    private PasswordField pstPassword;


    @FXML
    private TextField txtUserName;

    @FXML
    void actionLogIn(ActionEvent event) throws IOException {
        String enteredUsername = txtUserName.getText();
        String enteredPassword = pstPassword.getText();

        if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
            Parent dashboardRoot = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(dashboardRoot));
            stage.centerOnScreen();
            stage.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid Username or Password!").showAndWait();
        }
    }

    @FXML
    void btnOnActionLogIn(ActionEvent event) throws IOException {
        actionLogIn(event);
    }
    @FXML
    void hyperlinkFrogetPasswordOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/forgetPasswordForm1.fxml"));
        Scene scene = hyperPassword.getScene();
        root.translateXProperty().set(scene.getWidth());

        AnchorPane parentContainer = (AnchorPane) scene.getRoot();

        // Remove the existing content
        parentContainer.getChildren().clear();

        // Add the new content
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_BOTH);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

    }


    @FXML
    void hyperOnActionRegister(ActionEvent event) {

    }

    @FXML
    void pstOnActionPassword(ActionEvent event) {

    }


    @FXML
    void txtOnActionUserName(ActionEvent event) {

    }

}
