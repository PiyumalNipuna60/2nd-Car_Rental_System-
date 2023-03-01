package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class VehicleRent {
    @Id
    private String requestNumber;
    private String nicNumber;
    private String brand;
    private String rate;
    private Date rentStartDate;
    private Date rentEndDate;
    private String drName;

    @ManyToOne
    @JoinColumn(name="customer",referencedColumnName = "email")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="vehicle",referencedColumnName = "carNumber")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name="driver",referencedColumnName = "driverId")
    private Driver driver;
}