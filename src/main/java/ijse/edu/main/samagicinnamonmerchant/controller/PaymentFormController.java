package ijse.edu.main.samagicinnamonmerchant.controller;

import com.jfoenix.controls.JFXComboBox;
import ijse.edu.main.samagicinnamonmerchant.dto.PaymentDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.tm.PaymentTm;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.String.valueOf;

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
    private TableColumn<?, ?> colSupId;
    @FXML
    private TableColumn<?, ?> colTotalAmount;
    @FXML
    private Label lblPaymentId;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblCustomerId;
    @FXML
    private TextField txtAdvance;

    @FXML
    private TextField txtAmountToBePay;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private TextField txtTotalAmount;


    @FXML
    private TableView<PaymentTm> tblPayment;


    @FXML
    private TextField txtDate;
    @FXML
    private JFXComboBox<?> comBoxPaymentNo;
    @FXML
    private JFXComboBox<?> comBoxType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colPaymentNo.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
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
        loadNextPaymentId();
        loadTableData();

        txtAdvance.setText("");
        txtAmountToBePay.setText("");
        txtTotalAmount.setText("");
        txtAreaDescription.setText("");
    }
    public void loadNextPaymentId() throws SQLException {
        String nextPaymentId = paymentModel.getNextPaymentId();
        lblPaymentId.setText(nextPaymentId);
    }
    PaymentModel paymentModel = new PaymentModel();

    private void loadTableData() throws SQLException {
        ArrayList<PaymentDTO> paymentDTOS = paymentModel.getAllPayments();

        ObservableList<PaymentTm> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            PaymentTm paymentTm = new PaymentTm(
                    paymentDTO.getPaymentId(),
                    paymentDTO.getCustomerId(),
                    paymentDTO.getOrderId(),
                    paymentDTO.getTotalAmount(),
                    paymentDTO.getDate(),
                    paymentDTO.getAdvance(),
                    paymentDTO.getAmountToBePay(),
                    paymentDTO.getPaymentType(),
                    paymentDTO.getDescription(),
                    paymentDTO.getStatus()
            );
            paymentTMS.add(paymentTm);
        }
        tblPayment.setItems(paymentTMS);
    }



    @FXML
    void btnOnActionDelete(ActionEvent event) throws SQLException {
        String paymentId = lblPaymentId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = paymentModel.deletePayment(paymentId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Payment deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete payment...!").show();
            }
        }
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) throws SQLException {
        String paymentId = lblPaymentId.getText();
        String customerId = lblCustomerId.getText();
        String orderId = null;
        double totalAmount = Double.parseDouble(txtTotalAmount.getText());
        Date date = java.sql.Date.valueOf(LocalDate.now());
        double advance = Double.parseDouble(txtAdvance.getText());
        double amountToBePay = Double.parseDouble(txtAmountToBePay.getText());
        String paymentType = (String) comBoxType.getValue();
        String description = txtAreaDescription.getText();
        String status = null;

        PaymentDTO paymentDTO = new PaymentDTO(paymentId, customerId, orderId, totalAmount, date, advance, amountToBePay, paymentType, description, status);
        boolean isUpdated = paymentModel.addPayment(paymentDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment updated...!").show();
        }
    }
    @FXML
    void OnMouseClickedPayment(MouseEvent event) {

    }

    public void OnMouseClickedPayment(javafx.scene.input.MouseEvent mouseEvent) {
        PaymentTm paymentTm = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTm != null) {
            lblPaymentId.setText(paymentTm.getPaymentId());
            lblDate.setText(valueOf(paymentTm.getDate()));
            lblCustomerId.setText(paymentTm.getCustomerId());
            txtAdvance.setText(valueOf(paymentTm.getAdvance()));
            txtAmountToBePay.setText(valueOf(paymentTm.getAmountToBePay()));
            txtTotalAmount.setText(valueOf(paymentTm.getTotalAmount()));
            txtAreaDescription.setText(paymentTm.getDescription());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
        }
    }


}
