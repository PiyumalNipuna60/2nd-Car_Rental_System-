package lk.ijse.spring.repo;

import lk.ijse.spring.entity.VehicleRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface VehicleRentRepo extends JpaRepository<VehicleRent,String > {

    @Query(value = "select datediff(rentStartDate,rentEndDate)", nativeQuery = true)
    Long countByRentStartDateAndRentEndDate(Date rentStartDate, Date rentEndDate );

    @Query(value="SELECT requestNumber FROM booking ORDER BY requestNumber DESC LIMIT 1",nativeQuery = true)
    String getLastID();
}
