package ijse.edu.main.samagicinnamonmerchant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginPageController {
    private final String correctUsername = "chamindu";
    private final String correctPassword = "1234";

    @FXML
    private Button btnLogIn;

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
    void hyperOnActionRegister(ActionEvent event) {

    }

    @FXML
    void pstOnActionPassword(ActionEvent event) {

    }


    @FXML
    void txtOnActionUserName(ActionEvent event) {

    }

}
