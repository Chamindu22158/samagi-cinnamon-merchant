package ijse.edu.main.samagicinnamonmerchant.dto;

import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private String customerId;
    private String name;
    private String Address;
    private String contact;
    private String email;
    private int nic;
}
