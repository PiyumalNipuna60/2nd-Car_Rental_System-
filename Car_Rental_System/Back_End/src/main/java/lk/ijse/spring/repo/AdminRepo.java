package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,String> {
}
