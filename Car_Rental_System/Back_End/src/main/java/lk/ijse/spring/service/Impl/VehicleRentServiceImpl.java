package lk.ijse.spring.service.Impl;

import lk.ijse.spring.dto.AdminDTO;
import lk.ijse.spring.dto.VehicleRentDTO;
import lk.ijse.spring.entity.VehicleRent;
import lk.ijse.spring.repo.VehicleRentRepo;
import lk.ijse.spring.service.VehicleRentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VehicleRentServiceImpl implements VehicleRentService {

    @Autowired
    VehicleRentRepo vehicleRentRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void saveVehicleRent(VehicleRentDTO dto) {
        if (!vehicleRentRepo.existsById(dto.getRequestNumber())) {
            vehicleRentRepo.save(modelMapper.map(dto, VehicleRent.class));
        } else {
            throw new RuntimeException("Customer Already Exist..!");
        }
    }

    @Override
    public void deleteVehicleRent(String id) {
        if (vehicleRentRepo.existsById(id)){
            vehicleRentRepo.deleteById(id);
        }else{
            throw new RuntimeException("Please check the Customer ID.. No Such Customer..!");
        }
    }

    @Override
    public void updateVehicleRent(VehicleRentDTO dto) {
        if (vehicleRentRepo.existsById(dto.getRequestNumber())) {
            vehicleRentRepo.save(modelMapper.map(dto,VehicleRent.class));
        } else {
            throw new RuntimeException("No Such Customer To Update..! Please Check the ID..!");
        }
    }

    @Override
    public VehicleRentDTO searchVehicleRent(String id) {
        if (vehicleRentRepo.existsById(id)){
            return modelMapper.map(vehicleRentRepo.findById(id).get(), VehicleRentDTO.class);
        }else{
            throw new RuntimeException("No Customer For "+id+" ..!");
        }
    }

    @Override
    public List<VehicleRentDTO> getAllVehicleRents() {
        List<VehicleRent> all = vehicleRentRepo.findAll();
        return modelMapper.map(all,new TypeToken<List<AdminDTO>>(){
        }.getType());
    }

    @Override
    public Long countDate(Date rentStartDate, Date rentEndDate) {
        return null;
    }

    @Override
    public String getLastID() {
        String lastId = vehicleRentRepo.getLastID();
        if (lastId != null) {
            String[] split = lastId.split("R");
            int id = Integer.parseInt(split[1]);
            id++;
            if (id < 10) return "R00" + id;
            else if (id < 100) return "R0" + id;
            else return "R" + id;
        }else {
            return "R001";
        }
    }
}
