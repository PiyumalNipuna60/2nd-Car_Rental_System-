package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaintenanceRepo extends JpaRepository<Maintenance, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Maintenance SET cost=:cost WHERE maintenanceId=:maintenanceId", nativeQuery = true)
    void updateMaintenanceCost(@Param("maintenanceId") String maintenanceId, @Param("cost") double cost);

    @Query(value = "SELECT maintenanceId FROM Maintenance ORDER BY maintenanceId DESC LIMIT 1", nativeQuery = true)
    String generateMaintenanceId();

    @Query(value = "SELECT * FROM Maintenance WHERE cost=0", nativeQuery = true)
    List<Maintenance> getAllUnderMaintenances();
}
