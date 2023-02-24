package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Driver {
    @Id
    private String licenceNo;
    private String name;
    private String address;
    private int contactNo;
    private String nicNo;
    private String username;
    private String password;
    private final boolean availability = true;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    private List<CarRent> rentals = new ArrayList<>();
}
