package lk.ijse.spring.repo;

import lk.ijse.spring.entity.CarRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarRentRepo extends JpaRepository<CarRent, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE CarRent SET status=:status WHERE rentId=:rentId", nativeQuery = true)
    void updateCarRentStatus(@Param("rentId") String rentId, @Param("status") String status);

    List<CarRent> getAllByStatus(String status);

    @Query(value = "SELECT cr from CarRent cr where cr.status=:status AND cr.driver.licenceNo=:licenceNo", nativeQuery = true)
    List<CarRent> getAllByDrivingLicenceNo(@Param("status") String status, @Param("licenceNo") String licenceNo);

    @Query(value = "SELECT rentId FROM CarRent ORDER BY rentId DESC LIMIT 1",nativeQuery = true)
    String generateRentId();
}
