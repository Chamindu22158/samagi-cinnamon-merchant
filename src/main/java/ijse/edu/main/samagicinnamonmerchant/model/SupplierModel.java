package ijse.edu.main.samagicinnamonmerchant.model;

import ijse.edu.main.samagicinnamonmerchant.dto.SupplierDTO;
import ijse.edu.main.samagicinnamonmerchant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public static String getNextSupplierId() throws SQLException {
        ResultSet rst =  CrudUtil.execute("select supplierId from supplier order by supplierId desc limit 1");

        if(rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(1); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3
            System.out.println(String.format("S%03d",newIdIndex));
            return String.format("S%03d",newIdIndex);
        }
        System.out.println("S001");
        return  "S001";
    }

    public static ArrayList<SupplierDTO> getAllSuppliers() throws SQLException {
        ResultSet rst =  CrudUtil.execute("select * from supplier");

        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        while (rst.next()){
            SupplierDTO supplierDTO = new SupplierDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6)
            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;

    }

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO supplier VALUES(?,?,?,?,?,?)",
                supplierDTO.getSupplierId(),
                supplierDTO.getName(),
                supplierDTO.getAddress(),
                supplierDTO.getContact(),
                supplierDTO.getEmail(),
                supplierDTO.getNic()

        );
        return isSaved;
    }

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
        boolean isUpdated = CrudUtil.execute(
                "UPDATE supplier SET name=?,address=?,contact=?,email=?,nic=? WHERE supplierId=?",
                supplierDTO.getName(),
                supplierDTO.getAddress(),
                supplierDTO.getContact(),
                supplierDTO.getEmail(),
                supplierDTO.getNic(),
                supplierDTO.getSupplierId());


        return isUpdated;
    }
    public boolean deleteSupplier(String supplierId) throws SQLException {
        return CrudUtil.execute("delete from supplier where supplierId=?",supplierId);
    }
}
