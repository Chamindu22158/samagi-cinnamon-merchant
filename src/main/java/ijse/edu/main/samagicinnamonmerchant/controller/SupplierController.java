package ijse.edu.main.samagicinnamonmerchant.controller;

import ijse.edu.main.samagicinnamonmerchant.dto.SupplierDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.tm.SupplierTM;
import ijse.edu.main.samagicinnamonmerchant.model.SupplierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    @FXML
    private TableColumn<?, ?> ColSupplierId;

    @FXML
    private TableColumn<?, ?> ColAddress;

    @FXML
    private TableColumn<?, ?> ColContact;

    @FXML
    private TableColumn<?, ?> ColEmail;

    @FXML
    private TableColumn<?, ?> ColNIC;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtSupplierId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        ColNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));

        try {
           refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load supplier id").show();
        }
    }
    private void refreshPage() throws SQLException {
        loadNextSupplierId();
        loadTableData();

       // txtSupplierId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        txtNic.setText("");
    }

    public void loadNextSupplierId() throws SQLException {

        String nextSupplierId = SupplierModel.getNextSupplierId();
        txtSupplierId.setText(nextSupplierId);
    }
    SupplierModel supplierModel = new SupplierModel();
    private void loadTableData() throws SQLException {
        ArrayList<SupplierDTO> supplierDTOS = SupplierModel.getAllSuppliers();

        ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

        for (SupplierDTO supplierDTO : supplierDTOS) {
            SupplierTM supplierTM = new SupplierTM(
                    supplierDTO.getSupplierId(),
                    supplierDTO.getName(),
                    supplierDTO.getAddress(),
                    supplierDTO.getContact(),
                    supplierDTO.getEmail(),
                    supplierDTO.getNic()


            );
            supplierTMS.add(supplierTM);
        }

        tblSupplier.setItems(supplierTMS);
    }
    @FXML
    void btnOnActionDelete(ActionEvent event) throws SQLException {
        String supplierId = txtSupplierId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = supplierModel.deleteSupplier(supplierId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete supplier").show();
            }
        }
    }

    @FXML
    void btnOnActionReset(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void btnOnActionSave(ActionEvent event) throws SQLException {
        String supplierId = txtSupplierId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        int nic = Integer.parseInt(txtNic.getText());
        SupplierDTO supplierDTO = new SupplierDTO(supplierId, name, address, email, contact, nic);
        boolean isSaved = supplierModel.saveSupplier(supplierDTO);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save supplier").show();
        }
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) throws SQLException {
        String supplierId = txtSupplierId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        int nic = Integer.parseInt(txtNic.getText());
        SupplierDTO supplierDTO = new SupplierDTO(supplierId, name, address, email, contact, nic);
        boolean isUpdated = supplierModel.updateSupplier(supplierDTO);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update supplier").show();
        }
    }

    @FXML
    void txtOnActionAddress(ActionEvent event) {

    }

    @FXML
    void txtOnActionContact(ActionEvent event) {

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
    void txtOnActionSupplierId(ActionEvent event) {

    }






    public void onMouseClickedSupplier(javafx.scene.input.MouseEvent mouseEvent) {
        System.out.println("hhfgfsdd");
        SupplierTM supplierTM = tblSupplier.getSelectionModel().getSelectedItem();
            if (supplierTM != null) {
                txtSupplierId.setText(supplierTM.getSupplierId());
                txtName.setText(supplierTM.getName());
                txtAddress.setText(supplierTM.getAddress());
                txtEmail.setText(supplierTM.getEmail());
                txtContact.setText(supplierTM.getContact());
                txtNic.setText(String.valueOf(supplierTM.getNic()));

                btnSave.setDisable(true);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
        }
    }
}
