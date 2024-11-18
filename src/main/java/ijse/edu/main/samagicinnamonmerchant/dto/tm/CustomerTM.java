package ijse.edu.main.samagicinnamonmerchant.dto.tm;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerTM {
    private String customerId;
    private String name;
    private String Address;
    private String contact;
    private String email;
    private int nic;
}
