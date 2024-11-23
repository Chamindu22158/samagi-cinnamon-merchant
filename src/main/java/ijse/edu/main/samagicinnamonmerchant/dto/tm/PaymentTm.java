package ijse.edu.main.samagicinnamonmerchant.dto.tm;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTm {
    private String paymentId;
    private String customerId;
    private String orderId;
    private double totalAmount;
    private Date date;
    private double advance;
    private double amountToBePay;
    private String paymentType;
    private String description;
    private String status;




}
