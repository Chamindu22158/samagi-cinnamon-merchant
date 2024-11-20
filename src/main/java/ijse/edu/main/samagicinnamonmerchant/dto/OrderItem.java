package ijse.edu.main.samagicinnamonmerchant.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItem {
    private String itemId;
    private String orderId;
    private String itemName;
    private int weight;
}
