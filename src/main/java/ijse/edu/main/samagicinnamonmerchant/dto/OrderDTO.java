package ijse.edu.main.samagicinnamonmerchant.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private String orderId;
    private String customerId;
    private int qty;
    private Date date;
    private Date handOverDate;
    private String status;

}
