package ijse.edu.main.samagicinnamonmerchant.model;

import ijse.edu.main.samagicinnamonmerchant.dto.CustomerDTO;
import ijse.edu.main.samagicinnamonmerchant.dto.ItemDTO;
import ijse.edu.main.samagicinnamonmerchant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public static boolean saveItem(ItemDTO ItemDTO) throws SQLException {
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO customer VALUES(?,?,?,?)",
                ItemDTO.getItemId(),
                ItemDTO.getItemName(),
                ItemDTO.getOnHandWeight(),
                ItemDTO.getType());
        return isSaved;
    }
    public static String getNextItemId() throws SQLException {
        ResultSet rst =  CrudUtil.execute("select itemId from item order by itemId desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(1); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3

            return String.format("C%03d",newIdIndex);
        }
        return  "C001";
    }
    public ArrayList<ItemDTO> getAllItems() throws SQLException {
        ResultSet rst =  CrudUtil.execute("select * from Item");

        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        while (rst.next()){
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4)

            );
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }
}
