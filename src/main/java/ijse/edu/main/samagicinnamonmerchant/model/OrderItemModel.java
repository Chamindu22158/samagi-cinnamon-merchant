package ijse.edu.main.samagicinnamonmerchant.model;

import ijse.edu.main.samagicinnamonmerchant.dto.OrderItem;
import ijse.edu.main.samagicinnamonmerchant.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemModel {
    private final ItemModel itemModel = new ItemModel();
    public boolean saveOrderItemList(ArrayList<OrderItem> orderItemsDTOS) throws SQLException {
        for (OrderItem orderItem : orderItemsDTOS) {
            System.out.println("orderItem = " + orderItem);
            boolean isOrderItemSaved = saveOrderItem(orderItem);
            System.out.println("ddsdsds"+isOrderItemSaved);
            if (!isOrderItemSaved) {
                return false;
            }
            boolean isItemUpdated = itemModel.reduceQty(orderItem);
            if (!isItemUpdated) {
                return false;
            }
        }
        return true;
    }
        private boolean saveOrderItem(OrderItem orderItem) throws SQLException {
            return CrudUtil.execute(
                    "insert into orderItem (itemId,orderId,itemName,weight) values (?,?,?,?)",
                    orderItem.getItemId(),
                    orderItem.getOrderId(),
                    orderItem.getItemName(),
                    orderItem.getWeight());
        }
}
