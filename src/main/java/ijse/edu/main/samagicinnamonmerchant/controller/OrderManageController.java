package ijse.edu.main.samagicinnamonmerchant.controller;

import ijse.edu.main.samagicinnamonmerchant.dto.CustomerDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.ItemDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.OrderDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.OrderItem;
import ijse.edu.main.samagicinnamonmerchant.dto.tm.CartTM;
import ijse.edu.main.samagicinnamonmerchant.model.CustomerModel;
import ijse.edu.main.samagicinnamonmerchant.model.ItemModel;
import ijse.edu.main.samagicinnamonmerchant.model.OrderModel;
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

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderManageController implements Initializable {

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemPrice;

    @FXML
    private Label lblItemQty;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label orderDate;
    @FXML
    private TextField txtWeight;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableColumn<?, ?> colWeight;

    @FXML
    private TextField txtAddToCartQty;
    @FXML
    private TextField txtUnitPrice;

    private final OrderModel orderModel = new OrderModel();
    private final CustomerModel customerModel = new CustomerModel();
    private final ItemModel itemModel = new ItemModel();

    private final ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String selectedItemId = cmbItemId.getValue();
        String itemName = lblItemName.getText();
        double unitPrice = Double.parseDouble(lblItemPrice.getText());
        double weight = Double.parseDouble(txtWeight.getText());



        // If no item is selected, show an error alert and return
        if (selectedItemId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select item..!").show();
            return; // Exit the method if no item is selected.
        }

        String cartQtyString = txtWeight.getText();

        String qtyPattern = "^[0-9]+$";


        if (!cartQtyString.matches(qtyPattern)){
            new Alert(Alert.AlertType.ERROR, "Please enter valid quantity..!").show();
            return;
        }


        int cartQty = Integer.parseInt(cartQtyString);


        // Check if there are enough items in stock; if not, show an error alert and return
//        if (qtyOnHand < cartQty) {
//            new Alert(Alert.AlertType.ERROR, "Not enough items..!").show();
//            return; // Exit the method if there's insufficient stock.
//        }

        // Clear the text field for adding quantity after retrieving the input value.
        txtWeight.setText("");


        double total = unitPrice * cartQty;

        // Loop through each item in cart's observable list.
        for (CartTM cartTM : cartTMS) {

            // Check if the item is already in the cart
            if (cartTM.getItemId().equals(selectedItemId)) {
                // Update the existing CartTM object in the cart's observable list with the new quantity and total.
                int newQty = (int) (cartTM.getWeight() + cartQty);
                cartTM.setWeight(newQty); // Add the new quantity to the existing quantity in the cart.
                cartTM.setTotal(unitPrice * newQty); // Recalculate the total price based on the updated quantity

                // Refresh the table to display the updated information.
                tblCart.refresh();
                return; // Exit the method as the cart item has been updated.
            }
        }


        // Create a "Remove" button for the item to allow it to be removed from the cart later.
        Button btn = new Button("Remove");

        // If the item does not already exist in the cart, create a new CartTM object to represent it.
        CartTM newCartTM = new CartTM(
                selectedItemId,
                itemName,
                weight,
                unitPrice,
                total,
                btn
        );

        // Set an action for the "Remove" button, which removes the item from the cart when clicked.
        btn.setOnAction(actionEvent -> {

            // Remove the item from the cart's observable list (cartTMS).
            cartTMS.remove(newCartTM);

            // Refresh the table to reflect the removal of the item.
            tblCart.refresh();
        });

        // Add the newly created CartTM object to the cart's observable list.
        cartTMS.add(newCartTM);

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PaymentInfoForm.fxml"));
        Parent rootNode = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(rootNode));
        stage.centerOnScreen();
        stage.show();

        if (tblCart.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cart is empty..!").show();
            return;
        }
        if (cmbCustomerId.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select customer..!").show();
            return;
        }
        String orderId = lblOrderId.getText();
        String customerId = cmbCustomerId.getValue();
        Date date = java.sql.Date.valueOf(orderDate.getText());


        ArrayList<OrderItem> orderItemsDTOS = new ArrayList<>();
        for (CartTM cartTM : cartTMS) {
            OrderItem orderItems = new OrderItem(

                    cartTM.getItemId(),
                    orderId,
                    cartTM.getItemName(),
                    cartTM.getWeight()

            );
            orderItemsDTOS.add(orderItems);
        }
        OrderDTO orderDTO = new OrderDTO(
                orderId,
                customerId,
                date,
                orderItemsDTOS
        );

        boolean isSaved = orderModel.saveOrder(orderDTO);
        System.out.println(isSaved);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order placed successfully..!").show();
            refreshPage();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to place order..!").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException {
        refreshPage();
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException {
        CustomerDTO customerDTO = customerModel.findById(cmbCustomerId.getValue());
        if (customerDTO != null) {

            // FIll customer related labels
            lblCustomerName.setText(customerDTO.getName());
        }

    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException {
        String selectedItemId = cmbItemId.getSelectionModel().getSelectedItem();
        ItemDTO itemDTO = itemModel.findById(selectedItemId);

        // If item found (itemDTO not null)
        if (itemDTO != null) {

            // FIll item related labels
            lblItemName.setText(itemDTO.getItemName());
            lblItemQty.setText(String.valueOf(itemDTO.getOnHandWeight()));
            lblItemPrice.setText(String.valueOf(itemDTO.getPrice()));

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();


        try {
            refreshPage();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }
    }
    private void setCellValues() {

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));


        tblCart.setItems(cartTMS);
    }
    private void refreshPage() throws SQLException {
        lblOrderId.setText(orderModel.getNextOrderId());

        orderDate.setText(LocalDate.now().toString());


        loadCustomerIds();
        loadItemId();


        cmbCustomerId.getSelectionModel().clearSelection();
        cmbItemId.getSelectionModel().clearSelection();
        lblItemName.setText("");
        lblItemQty.setText("");
        txtWeight.setText("");
        lblItemPrice.setText("");
        lblCustomerName.setText("");


        cartTMS.clear();


        tblCart.refresh();
    }

    private void loadItemId() throws SQLException {
        ArrayList<String> itemIds = ItemModel.getAllItemIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(itemIds);
        cmbItemId.setItems(observableList);
    }

    private void loadCustomerIds() throws SQLException {
        ArrayList<String> customerIds = customerModel.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        cmbCustomerId.setItems(observableList);
    }

}
