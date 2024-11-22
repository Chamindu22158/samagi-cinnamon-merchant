package ijse.edu.main.samagicinnamonmerchant.model;


import ijse.edu.main.samagicinnamonmerchant.dto.ItemDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.OrderItem;
import ijse.edu.main.samagicinnamonmerchant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean saveItem(ItemDTO ItemDTO) throws SQLException {
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO item (itemId,itemName,type,onHandWeight,price) VALUES(?,?,?,?,?)",
                ItemDTO.getItemId(),
                ItemDTO.getItemName(),
                ItemDTO.getType(),
                ItemDTO.getOnHandWeight(),
                ItemDTO.getPrice()
        );
        return isSaved;
    }

    public static String getNextItemId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select itemId from item order by itemId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(1); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i + 1; // 3
            System.out.println(String.format("I%03d", newIdIndex));
            return String.format("I%03d", newIdIndex);
        }
        System.out.println("I001");
        return "I001";
    }

    public static boolean updateItem(ItemDTO itemDTO) throws SQLException {
        boolean isUpdated = CrudUtil.execute(
                "UPDATE Item SET itemName=?, type=?, onHandWeight=? , price=? WHERE itemId=?",
                itemDTO.getItemName(),
                itemDTO.getType(),
                itemDTO.getOnHandWeight(),
                itemDTO.getPrice(),
                itemDTO.getItemId()

        );
        return isUpdated;
    }

    public ArrayList<ItemDTO> getAllItems() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from Item");

        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(5),
                    rst.getDouble(6)


            );
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    public static boolean deleteItem(String itemId) throws SQLException {
        return CrudUtil.execute("delete from item where itemId=?", itemId);
    }

    public static ArrayList<String> getAllItemIds() throws SQLException {
        // Execute SQL query to get all item IDs
        ResultSet rst = CrudUtil.execute("select itemId from item");

        // Create an ArrayList to store the item IDs
        ArrayList<String> itemIds = new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return itemIds;
    }

    public ItemDTO findById(String selectedItemId) throws SQLException {
        // Execute SQL query to find the item by ID
        ResultSet rst = CrudUtil.execute("select * from item where itemId=?", selectedItemId);

        // If the item is found, create an ItemDTO object with the retrieved data
        if (rst.next()) {
            return new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(5),
                    rst.getDouble(6)
            );
        }
        // Return null if the item is not found
        return null;
    }
    public boolean reduceQty(OrderItem orderItem) throws SQLException {
        return CrudUtil.execute("UPDATE item SET onHandWeight = onHandWeight - ? WHERE itemId = ?", orderItem.getWeight(), orderItem.getItemId());
    }

}
