package ijse.edu.main.samagicinnamonmerchant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDelete1;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<?, ?> colAdvance;

    @FXML
    private TableColumn<?, ?> colAmountToBePay;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPaymentNo;

    @FXML
    private TableColumn<?, ?> colPaymentType;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtAdvance;

    @FXML
    private TextField txtAmountToBePay;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private TextField txtDate;

    @FXML
    void btnOnActionPendingPayments(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PendingPayment.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    void btnOnActionDelete(ActionEvent event) {

    }

    @FXML
    void btnOnActionSave(ActionEvent event) {

    }

}