package ijse.edu.main.samagicinnamonmerchant.dto.tm;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemTM {
    private String itemId;
    private String itemName;
    private String type;
    private double onHandWeight;
}
