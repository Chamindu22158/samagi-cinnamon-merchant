package ijse.edu.main.samagicinnamonmerchant.controller;

import ijse.edu.main.samagicinnamonmerchant.dto.CustomerDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.tm.CustomerTM;
import ijse.edu.main.samagicinnamonmerchant.model.CustomerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerContoller implements Initializable {

    @FXML
    private TableColumn<?, ?> ColAddress;

    @FXML
    private TableColumn<?, ?> ColContact;

    @FXML
    private TableColumn<?, ?> ColCustomerId;

    @FXML
    private TableColumn<?, ?> ColEmail;

    @FXML
    private TableColumn<?, ?> ColNIC;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private Button btnDelete;
    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhone;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }
    private void refreshPage() throws SQLException {
        loadNextCustomerId();
        loadTableData();



        txtName.setText("");
        txtNic.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
    }

    public void loadNextCustomerId() throws SQLException {

        String nextCustomerId = customerModel.getNextCustomerId();
        txtCustomerId.setText(nextCustomerId);
    }

    CustomerModel customerModel = new CustomerModel();

    private void loadTableData() throws SQLException {
        ArrayList<CustomerDTO> customerDTOS = customerModel.getAllCustomers();

        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDTO customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCustomerId(),
                    customerDTO.getName(),
                    customerDTO.getAddress(),
                    customerDTO.getContact(),
                    customerDTO.getEmail(),
                    customerDTO.getNic()


            );
            customerTMS.add(customerTM);
        }

        tblCustomer.setItems(customerTMS);
    }



    @FXML
    void btnOnActionDelete(ActionEvent event) throws SQLException {
        String customerId = txtCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES){

            boolean isDeleted = customerModel.deleteCustomer(customerId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
            }
        }
    }

    @FXML
    void btnOnActionReset(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnOnActionSave(ActionEvent event)  throws SQLException {
        String CusId = txtCustomerId.getText();
        String CusName = txtName.getText();
        int CusNic = Integer.parseInt(txtNic.getText());
        String CusEmail = txtEmail.getText();
        String CusContact = txtPhone.getText();
        String CusAddress = txtAddress.getText();

        txtName.setStyle(txtName.getStyle()+";-fx-border-color: #7367F0;");
        txtNic.setStyle(txtNic.getStyle()+";-fx-border-color: #7367F0;");
        txtEmail.setStyle(txtEmail.getStyle()+";-fx-border-color: #7367F0;");
        txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^07[0-9]\\d{7}$\n";

        boolean isValidName = CusName.matches(namePattern);
        boolean isValidNic = String.valueOf(CusNic).matches(nicPattern);
        boolean isValidEmail = CusEmail.matches(emailPattern);
        boolean isValidPhone = CusContact.matches(phonePattern);
        if (!isValidName){
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle()+";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValidNic){
            txtNic.setStyle(txtNic.getStyle()+";-fx-border-color: red;");
//            return;
        }

        if (!isValidEmail){
            txtEmail.setStyle(txtEmail.getStyle()+";-fx-border-color: red;");
        }

        if (!isValidPhone){
            txtPhone.setStyle(txtPhone.getStyle()+";-fx-border-color: red;");
        }

        if (isValidName && isValidNic && isValidEmail && isValidPhone){
            CustomerDTO customerDTO = new CustomerDTO(CusId, CusName, CusAddress, CusContact, CusEmail,CusNic);

            boolean isSaved = customerModel.saveCustomer(customerDTO);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to save customer").show();
            }
        }


    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) throws SQLException {
        String CusId = txtCustomerId.getText();
        String CusName = txtName.getText();
        int CusNic = Integer.parseInt(txtNic.getText());
        String CusEmail = txtEmail.getText();
        String CusContact = txtPhone.getText();
        String CusAddress = txtAddress.getText();

        CustomerDTO customerDTO = new CustomerDTO(CusId, CusName, CusAddress, CusContact, CusEmail,CusNic);

        boolean isUpdated = customerModel.updateCustomer(customerDTO);
        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
            refreshPage();
        }else {
            new Alert(Alert.AlertType.ERROR, "Fail to update customer").show();
        }

    }
    @FXML
    void onMouseClickTblCustomer(MouseEvent event) {
        CustomerTM customerTM = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTM != null){
            txtCustomerId.setText(customerTM.getCustomerId());
            txtName.setText(customerTM.getName());
            txtNic.setText(String.valueOf(customerTM.getNic()));
            txtEmail.setText(customerTM.getEmail());
            txtPhone.setText(customerTM.getContact());
            txtAddress.setText(customerTM.getAddress());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    @FXML
    void txtOnActionCustomerId(ActionEvent event) {

    }

    @FXML
    void txtOnActionEmail(ActionEvent event) {

    }

    @FXML
    void txtOnActionName(ActionEvent event) {

    }

    @FXML
    void txtOnActionNic(ActionEvent event) {

    }

    @FXML
    void txtOnActionPhone(ActionEvent event) {

    }

    @FXML
    void txtOnActionAddress(ActionEvent event) {
    }
}
