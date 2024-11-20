package ijse.edu.main.samagicinnamonmerchant.dto;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDTO {
    private String supplierId;
    private String name;
    private String Address;
    private String email;
    private String contact;
    private int nic;
}
