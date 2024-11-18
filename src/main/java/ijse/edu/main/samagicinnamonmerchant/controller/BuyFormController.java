package ijse.edu.main.samagicinnamonmerchant.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BuyFormController {

    @FXML
    private TableColumn<?, ?> colBuyingPrice;

    @FXML
    private TableColumn<?, ?> colCuttingAmount;

    @FXML
    private TableColumn<?, ?> colNetWeight;

    @FXML
    private TableColumn<?, ?> colProductType;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colWeight;

    @FXML
    private JFXComboBox<?> comBoxContact;

    @FXML
    private JFXComboBox<?> comBoxName;

    @FXML
    private Label lblNetWeight;

    @FXML
    private Label lblOrderNo;

    @FXML
    private Label lblProductType;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TextField txtBuyingPrice;

    @FXML
    private TextField txtCuttingAmount;

    @FXML
    private TextField txtWeight;

    @FXML
    void btnOnActionAddToCart(ActionEvent event) {

    }

    @FXML
    void btnOnActionCinnamon(ActionEvent event) {

    }

    @FXML
    void btnOnActionOther(ActionEvent event) {

    }

    @FXML
    void btnOnActionPlaceOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PaymentInfoForm.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void comBoxOnActionContact(KeyEvent event) {

    }

    @FXML
    void comBoxOnActionName(KeyEvent event) {

    }

    @FXML
    void comBoxOnActionSearchContact1(KeyEvent event) {

    }

    @FXML
    void comBoxOnActionSearchName1(KeyEvent event) {

    }

}
