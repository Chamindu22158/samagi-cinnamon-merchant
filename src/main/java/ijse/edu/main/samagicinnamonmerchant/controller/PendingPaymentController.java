package ijse.edu.main.samagicinnamonmerchant.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PendingPaymentController {

    @FXML
    private TableColumn<?, ?> colAdvance;

    @FXML
    private TableColumn<?, ?> colAmountToBePay;

    @FXML
    private TableColumn<?, ?> colAmountToBePaySupplier;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colPaymentNo;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableColumn<?, ?> colTotalAmountSupplier;

    @FXML
    private JFXComboBox<?> comBoxPaymentNo;

    @FXML
    private JFXComboBox<?> comBoxSupplierContact;

    @FXML
    private Label lblAmountToBePay;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private TableView<?> tblPendingPayments;

    @FXML
    private TableView<?> tblSupplierSearch;

    @FXML
    private TextField txtAdvance;

    @FXML
    private TextField txtDate;

    @FXML
    void OnMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnOnActionPayAdvance(ActionEvent event) {

    }

    @FXML
    void comBoxOnActionContact(KeyEvent event) {

    }

    @FXML
    void comBoxOnActionPaymentNo(KeyEvent event) {

    }

    @FXML
    void comBoxOnActionSearchContact(KeyEvent event) {

    }

    @FXML
    void comBoxOnActionSearchPaymentNo(ActionEvent event) {

    }

}
