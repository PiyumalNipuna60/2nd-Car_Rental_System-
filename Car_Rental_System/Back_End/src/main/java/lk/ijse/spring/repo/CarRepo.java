package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CarRepo extends JpaRepository<Car, String> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Car SET status=:status WHERE registrationNO=:registrationNO", nativeQuery = true)
    void updateCarStatus(@Param("status") String status, @Param("registrationNO") String registrationNO);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Car SET frontViewImg=:frontViewImg,backViewImg=:backViewImg,internalViewImg=:internalViewImg,sideViewImg=:sideViewImg WHERE registrationNO=:registrationNO",nativeQuery = true)
    void updateCarFilePaths(@Param("frontViewImg") String frontViewImg, @Param("backViewImg") String backViewImg, @Param("internalViewImg") String internalViewImg, @Param("sideViewImg") String sideViewImg, @Param("registrationNO") String registrationNO);

    @Query(value = "SELECT * FROM Car WHERE status=:status",nativeQuery = true)
    List<Car> getAllCarsByStatus(@Param("status") String status);

    @Query(value = "SELECT COUNT(registrationNO) FROM Car WHERE status=:status",nativeQuery = true)
    int getCountOfCarsByStatus(@Param("status") String status);
}
