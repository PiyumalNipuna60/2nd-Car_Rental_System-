package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class CarRent {
    private String rentId;
    @Id
    private LocalDate date;
    private LocalDate pickUpDate;
    private LocalDate returnDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "registrationNO", referencedColumnName = "registrationNO",nullable = false)
    private Car car;

}
