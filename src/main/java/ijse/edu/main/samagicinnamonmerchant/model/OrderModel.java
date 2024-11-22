package ijse.edu.main.samagicinnamonmerchant.model;

import ijse.edu.main.samagicinnamonmerchant.db.DBConnection;
import ijse.edu.main.samagicinnamonmerchant.dto.OrderDTO;
import ijse.edu.main.samagicinnamonmerchant.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    private final OrderItemModel   orderItemModel = new OrderItemModel();
    public String getNextOrderId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select orderId from orders order by orderId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("O%03d", newIdIndex);
        }

        return "O001";
    }

    public boolean saveOrder(OrderDTO orderDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isOrderSaved = CrudUtil.execute(
                    "INSERT INTO orders VALUES(?,?,?)",
                    orderDTO.getOrderId(),
                    orderDTO.getCustomerId(),
                    orderDTO.getDate()
            );
            System.out.println("save order"+isOrderSaved);
            if (isOrderSaved) {
                boolean isOrderItemSaved = orderItemModel.saveOrderItemList(orderDTO.getOrderItemsDTOS());

                if (isOrderItemSaved) {
                    connection.commit();
                    return true;
                }
                System.out.println("save orderItem"+isOrderItemSaved);
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
