package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,String> {
    Optional<Customer> findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Optional<Customer> findByNic(String nic);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Customer SET nicFrontImg=:nicFrontImg,nicBackImg=:nicBackImg,licenceImg=:licenceImg WHERE customerId=:customerId", nativeQuery = true)
    void updateCustomerFilePaths(@Param("nicFrontImg") String nicFrontImg, @Param("nicBackImg") String nicBackImg, @Param("licenceImg") String licenceImg, @Param("customerId") String customerId);

}
