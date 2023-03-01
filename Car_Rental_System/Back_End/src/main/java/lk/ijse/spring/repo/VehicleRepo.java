package lk.ijse.spring.repo;
import lk.ijse.spring.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepo extends JpaRepository<Vehicle,String> {
    Optional<Vehicle> findByBrand(String brand);
}
