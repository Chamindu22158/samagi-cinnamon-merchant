package ijse.edu.main.samagicinnamonmerchant.dto.tm;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierTM extends ItemTM {
    private String supplierId;
    private String name;
    private String Address;
    private String email;
    private String contact;
    private String nic;
}
