package ijse.edu.main.samagicinnamonmerchant.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem {
    private String itemId;
    private String orderId;
    private String itemName;
    private double weight;
}
