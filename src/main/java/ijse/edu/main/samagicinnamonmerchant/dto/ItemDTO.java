package ijse.edu.main.samagicinnamonmerchant.dto;

import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String itemId;
    private String itemName;
    private String type;
    private double onHandWeight;
    private double price;


}
