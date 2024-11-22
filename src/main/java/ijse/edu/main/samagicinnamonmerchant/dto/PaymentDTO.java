package ijse.edu.main.samagicinnamonmerchant.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDTO {
    private String paymentId;
    private String supplierId;
    private Date date;
    private double totalAmount;
    private double advance;
    private double amountToBePay;
    private String description;
    private String status;

}
