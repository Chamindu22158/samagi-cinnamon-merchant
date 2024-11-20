package ijse.edu.main.samagicinnamonmerchant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Appinitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/LoginPage.fxml")));
        stage.setScene(new Scene(root));
        stage.setTitle("Samagi Cinnamon Merchant");
        stage.centerOnScreen();
        stage.show();

    }
}
