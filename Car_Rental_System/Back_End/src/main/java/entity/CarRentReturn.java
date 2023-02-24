package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class CarRentReturn {
    @Id
    private String returnId;
    private LocalDate date;
    private double noOfKm;
}
