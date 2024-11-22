package ijse.edu.main.samagicinnamonmerchant.model;

import ijse.edu.main.samagicinnamonmerchant.dto.PaymentDTO;
import ijse.edu.main.samagicinnamonmerchant.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {

    public String getNextPaymentId() throws SQLException {
        ResultSet rst =  CrudUtil.execute("select paymentId from payment order by paymentId desc limit 1");

        if (rst.next()){
            String lastId = rst.getString(1); // P002
            String substring = lastId.substring(1); // 002
            int i = Integer.parseInt(substring); // 2
            int newIdIndex = i+1; // 3

            return String.format("P%03d",newIdIndex);
        }
        return  "P001";
    }
    public ArrayList<PaymentDTO> getAllPayments() throws SQLException {
        ResultSet rst =  CrudUtil.execute("select * from payment");

        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        while (rst.next()){
            PaymentDTO paymentDTO = new PaymentDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getString(7),
                    rst.getString(8));
            paymentDTOS.add(paymentDTO);
        }

        return paymentDTOS;
    }
}
