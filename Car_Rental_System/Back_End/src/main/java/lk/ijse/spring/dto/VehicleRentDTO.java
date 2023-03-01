package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class VehicleRentDTO {
    private String requestNumber;
    private String nicNumber;
    private String brand;
    private String rate;
    private Date rentStartDate;
    private Date rentEndDate;
    private String drName;
}
