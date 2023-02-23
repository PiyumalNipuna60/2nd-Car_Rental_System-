package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverDTO {
    private String licenceNo;
    private String name;
    private String address;
    private int contactNo;
    private String nicNo;
    private String username;
    private String password;
    private final boolean availability = true;
}
