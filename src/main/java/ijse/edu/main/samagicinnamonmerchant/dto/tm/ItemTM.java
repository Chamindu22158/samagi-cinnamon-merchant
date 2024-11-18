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
    private double onHandWeight;
    private String type;
}
