package ijse.edu.main.samagicinnamonmerchant.controller;

import com.jfoenix.controls.JFXComboBox;
import ijse.edu.main.samagicinnamonmerchant.dto.PaymentDTO;
import ijse.edu.main.samagicinnamonmerchant.model.PaymentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

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
    private TableColumn<?, ?> colcusId;

    @FXML
    private TableColumn<?, ?> colTotalAmount;
    @FXML
    private Label lblPaymentId;

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
    private JFXComboBox<?> comBoxPaymentNo;
    @FXML
    private JFXComboBox<?> comBoxType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colcusId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colPaymentNo.setCellValueFactory(new PropertyValueFactory<>("paymentNo"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colAmountToBePay.setCellValueFactory(new PropertyValueFactory<>("amountToBePay"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load supplier id").show();
        }
    }
    private void refreshPage() throws SQLException {


    }
    public void loadNextPaymentId() throws SQLException {
        String nextPaymentId = paymentModel.getNextPaymentId();
        lblPaymentId.setText(nextPaymentId);
    }
    PaymentModel paymentModel = new PaymentModel();

    private void loadTableData() throws SQLException {
        ArrayList<PaymentDTO> paymentDTOS = paymentModel.getAllPayments();

        ObservableList<PaymentDTO> paymentTMS = FXCollections.observableArrayList();

//        for (PaymentDTO paymentDTO : paymentDTOS) {
//            PaymentTm paymentTm = new PaymentTm(
//
//            )
//        }
    }

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
private void loadTypeComBox(){
    ObservableList<String> types = FXCollections.observableArrayList();
    types.add("Cash");
    types.add("Card");
    //comBoxType.setItems(types);
}

}
