package lk.ijse.spring.service;

import lk.ijse.spring.dto.VehicleRentDTO;

import java.util.Date;
import java.util.List;

public interface VehicleRentService {
    void saveVehicleRent(VehicleRentDTO dto);

    void deleteVehicleRent(String id);

    void updateVehicleRent(VehicleRentDTO dto);

    VehicleRentDTO searchVehicleRent(String id);

    List<VehicleRentDTO> getAllVehicleRents();

    Long countDate(Date rentStartDate, Date rentEndDate);

    String getLastID();
}
