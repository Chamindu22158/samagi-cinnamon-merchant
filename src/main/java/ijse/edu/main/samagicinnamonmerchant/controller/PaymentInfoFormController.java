package ijse.edu.main.samagicinnamonmerchant.controller;



import com.jfoenix.controls.JFXComboBox;
import ijse.edu.main.samagicinnamonmerchant.db.DBConnection;
import ijse.edu.main.samagicinnamonmerchant.dto.PaymentDTO;
import ijse.edu.main.samagicinnamonmerchant.model.ItemModel;
import ijse.edu.main.samagicinnamonmerchant.model.PaymentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static java.sql.Date.valueOf;

public class PaymentInfoFormController implements Initializable {

    @FXML
    private JFXComboBox<String> comboPayment;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOrderNo;

    @FXML
    private Label lblPaymentNo;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private TextArea txtAreaDescription;

    @FXML
    private TextField txtPayAmount;
    private final PaymentModel paymentModel = new PaymentModel();
    public String cusId;
    public String total;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();

        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     void setData() {
        lblOrderNo.setText(OrderManageController.orderId1);
        lblTotalAmount.setText(OrderManageController.StaticTotal1);
         String customerId = OrderManageController.StaticCustomerId1;
         String Total = OrderManageController.StaticTotal1;
         System.out.println("54545"+customerId+"kjij"+Total);
    }

    private void refreshPage() throws SQLException {

        lblDate.setText(LocalDate.now().toString());

        try {
            lblPaymentNo.setText(paymentModel.getNextPaymentId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("Cash");
        types.add("Card");
        comboPayment.setItems(types);
    }

    @FXML
    void btnOnActionPayNow(ActionEvent event) throws SQLException, JRException {
        String paymentId = lblPaymentNo.getText();
        String orderId = lblOrderNo.getText();
        String customerId = OrderManageController.StaticCustomerId1;
        String Total = OrderManageController.StaticTotal1;
        System.out.println(customerId+Total);
        String description = txtAreaDescription.getText();
        String paymentType = comboPayment.getValue();
        double payAmount = Double.parseDouble(txtPayAmount.getText());
        double totalAmount = Double.parseDouble(lblTotalAmount.getText());
        double balance = totalAmount - payAmount;
        Date date = valueOf(LocalDate.now());

        PaymentDTO paymentDTO = new PaymentDTO(paymentId, customerId, orderId, totalAmount, date, payAmount, balance, paymentType, description, "Paid");

        boolean isAdded = paymentModel.addPayment(paymentDTO);
        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Info");
            alert.setHeaderText("Payment Info");
            alert.setContentText("Payment added Successfully");

            alert.showAndWait();
            printBill();}

    }

    private void printBill() throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/OrderReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        Map<String,Object> data = new HashMap<>();
        data.put("OrderID",OrderManageController.orderId1);


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

}
