package ijse.edu.main.samagicinnamonmerchant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private Button btnBuy;
    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnItem;

    @FXML
    private AnchorPane content;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnSupplier;

    @FXML
    private ImageView imgLogo;
    @FXML
    void navigateToBuyingPage(ActionEvent event) {
        navigateTo("/view/BuyForm.fxml");
    }

    @FXML
    void navigateToCustomerPage(ActionEvent event) throws IOException {
        navigateTo("/view/Customer.fxml");
    }

    @FXML
    void navigateToItemPage(ActionEvent event) {
        navigateTo("/view/Item.fxml");
    }

    @FXML
    void navigateToOrderPage(ActionEvent event) {
        navigateTo("/view/OrderManage.fxml");
    }
    @FXML
    void navigateToHomePage(ActionEvent event) {
        navigateTo("/view/HomePage.fxml");
    }

    @FXML
    void navigateToPaymentPage(ActionEvent event) {
        navigateTo("/view/PaymentForm.fxml");
    }

    @FXML
    void navigateToSupplierPage(ActionEvent event) {
        navigateTo("/view/Supplier.fxml");
    }
    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));


            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());

            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/Customer.fxml");
    }

}
