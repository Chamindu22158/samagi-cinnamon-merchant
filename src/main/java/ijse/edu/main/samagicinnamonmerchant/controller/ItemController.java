package ijse.edu.main.samagicinnamonmerchant.controller;

import com.jfoenix.controls.JFXComboBox;
import ijse.edu.main.samagicinnamonmerchant.dto.CustomerDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.ItemDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.tm.CustomerTM;
import ijse.edu.main.samagicinnamonmerchant.dto.tm.ItemTM;
import ijse.edu.main.samagicinnamonmerchant.model.ItemModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemController  implements Initializable {

    @FXML
    private AnchorPane AnchorPaneItem;

    @FXML
    private Button btnAddItem;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<ItemTM> tblItem;


    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colOnHandWeight;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private JFXComboBox<String> comBoxType;

    @FXML
    private TextField txtId;


    @FXML
    private TextField txtItemName;

    @FXML
    private Text txtName;

    @FXML
    private TextField txtOnHandWeight;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colOnHandWeight.setCellValueFactory(new PropertyValueFactory<>("onHandWeight"));

        try {
            refreshPage();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load item id").show();
        }
    }
    private void refreshPage() throws SQLException {

        txtId.setText("");
        txtItemName.setText("");
        comBoxType.setValue(null);
        txtOnHandWeight.setText("");

        loadNextItemId();
        loadTableData();
        loadTypeToComBox();


    }

    private void loadTypeToComBox() {
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("Cinnamon");
        types.add("Other");

        comBoxType.setItems(types);
    }

    public void loadNextItemId() throws SQLException {
        String nextItemId = ItemModel.getNextItemId();
        txtId.setText( ItemModel.getNextItemId());
    }
    ItemModel itemModel = new ItemModel();
    private void loadTableData() throws SQLException {
        ArrayList<ItemDTO> itemDTOS = itemModel.getAllItems();

        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();

        for (ItemDTO itemDTO : itemDTOS) {

           ItemTM itemTM = new ItemTM(
                    itemDTO.getItemId(),
                    itemDTO.getItemName(),
                    itemDTO.getType(),
                   itemDTO.getOnHandWeight()

            );
            itemTMS.add(itemTM);
        }
        tblItem.setItems(itemTMS);
    }
    @FXML
    void btnOnActionAddItem(ActionEvent event)  throws SQLException{
        String itemId = txtId.getText();
        String itemName = txtItemName.getText();
        double onHandWeight = Double.parseDouble(txtOnHandWeight.getText());
        String type = comBoxType.getValue();
        ItemDTO itemDTO = new ItemDTO(itemId, itemName, type, onHandWeight);

        boolean isSaved = ItemModel.saveItem(itemDTO);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item Saved").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save Item").show();
        }
    }

    @FXML
    void btnOnActionDelete(ActionEvent event) throws SQLException {
        String itemId = txtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.OK) {
            boolean isDeleted = itemModel.deleteItem(itemId);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Item").show();
            }
        }
    }

    @FXML
    void btnOnActionSave(MouseEvent event) {

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) throws SQLException {
        String itemId = txtId.getText();
        String itemName = txtItemName.getText();
        String type = comBoxType.getValue().toString();
        double onHandWeight = Double.parseDouble(txtOnHandWeight.getText());

        ItemDTO itemDTO = new ItemDTO(itemId, itemName, type, onHandWeight);

        boolean isUpdated = ItemModel.updateItem(itemDTO);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item Updated").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to update Item").show();
        }
    }
    @FXML
    void onMouseClickTblItem(MouseEvent event) {
        ItemTM itemTM = tblItem.getSelectionModel().getSelectedItem();
        if(itemTM != null){
            txtId.setText(itemTM.getItemId());
            txtItemName.setText(itemTM.getItemName());
            comBoxType.setValue(itemTM.getType());
            txtOnHandWeight.setText(String.valueOf(itemTM.getOnHandWeight()));
        }
    }
    @FXML
    void comBoxOnActionType(ActionEvent event) {

    }

    @FXML
    void txtOnActionItemId(ActionEvent event) {

    }

    @FXML
    void txtOnActionItemName(ActionEvent event) {

    }

    @FXML
    void txtOnActionOnHandWeight(ActionEvent event) {

    }



}
