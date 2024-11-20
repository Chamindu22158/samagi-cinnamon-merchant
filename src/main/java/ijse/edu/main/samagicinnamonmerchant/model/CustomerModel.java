package ijse.edu.main.samagicinnamonmerchant.model;

import ijse.edu.main.samagicinnamonmerchant.dto.CustomerDTO;
import ijse.edu.main.samagicinnamonmerchant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO customer VALUES(?,?,?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getContact(),
                customerDTO.getEmail(),
                customerDTO.getNic());

        return isSaved;
    }

    public static boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        boolean isUpdated = CrudUtil.execute(
                "UPDATE Customer SET name=?, nic=?, email=?, contact=?, address=? WHERE customerId=?",
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getContact(),
                customerDTO.getAddress(),
                customerDTO.getCustomerId()

        );
        return isUpdated;
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException {
        ResultSet rst =  CrudUtil.execute("select * from customer");

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        while (rst.next()){
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6)
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public String getNextCustomerId() throws SQLException {
        ResultSet rst =  CrudUtil.execute("select customerId from customer order by customerId desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // C002
            String substring = lastId.substring(1); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3

            return String.format("C%03d",newIdIndex);
        }
        return  "C001";
    }
    public boolean deleteCustomer(String customerId) throws SQLException {
        return CrudUtil.execute("delete from customer where customerId=?",customerId);
    }
    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select customerId from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }

    public CustomerDTO findById(String selectedCusId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer where customerId=?", selectedCusId);

        if (rst.next()) {
            return new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6)
            );
        }
        return null;
    }
}

