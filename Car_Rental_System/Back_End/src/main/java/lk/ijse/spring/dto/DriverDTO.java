package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DriverDTO {
    private String driverId;
    private String name;
    private String address;
    private int age;
    private String contact;
    private String gender;
    private String nic;
    private String drivingLicenseNo;
    private String email;
    private String password;
}
