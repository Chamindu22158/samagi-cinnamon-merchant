package ijse.edu.main.samagicinnamonmerchant.controller;

import com.jfoenix.controls.JFXComboBox;
import ijse.edu.main.samagicinnamonmerchant.model.SupplierItemModel;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BuyFormController implements Initializable {

    @FXML
    private AnchorPane buyPain;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colBuyingPrice.setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        colCuttingAmount.setCellValueFactory(new PropertyValueFactory<>("cuttingAmount"));
        colNetWeight.setCellValueFactory(new PropertyValueFactory<>("netWeight"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            refreshPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load supplier id").show();
        }
    }
    private void refreshPage(){
//        loadNextCustomerId();
//        loadTableData();

        lblProductType.setLabelFor(comBoxName);
        lblNetWeight.setLabelFor(comBoxContact);
        comBoxContact.setValue(null);
        txtCuttingAmount.setText("0");
        txtBuyingPrice.setText("0");
    }
//    private void loadNextOrderId() throws SQLException {
//        String nextOrderId = SupplierItemModel.getNextSupplierId();
//        lblOrderNo.setText(nextCustomerId);
//    }
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
