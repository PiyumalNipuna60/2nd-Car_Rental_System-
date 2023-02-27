package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment, String> {

    @Query(value = "SELECT * FROM Payment WHERE date BETWEEN :fromDate TO :toDate", nativeQuery = true)
    List<Payment> getAllPaymentsByDateRange(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);


    @Query(value = "SELECT * FROM Payment WHERE customerId=:customerId", nativeQuery = true)
    List<Payment> getAllPaymentsByCustomerId(@Param("customerId") String customerId);

    @Query(value = "SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1", nativeQuery = true)
    String generatePaymentId();
}
