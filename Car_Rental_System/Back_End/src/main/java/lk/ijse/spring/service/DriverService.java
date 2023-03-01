package lk.ijse.spring.service;
import lk.ijse.spring.dto.DriverDTO;

import java.util.List;

public interface DriverService {
     void saveDriver(DriverDTO dto);

     void deleteDriver(String id);

     void updateDriver(DriverDTO dto);

     DriverDTO searchDriver(String id);

     List<DriverDTO> getAllDrivers();

     DriverDTO findName(String name);

     DriverDTO findEmailAndPassword(String email, String password);


}
