package ijse.edu.main.samagicinnamonmerchant.dto.tm;


import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTM {
    private String itemId;
    private String itemName;
    private int qty;
    private double unitPrice;
    private double total;
    private Button removeBtn;
}
