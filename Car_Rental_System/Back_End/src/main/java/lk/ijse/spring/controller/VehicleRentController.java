package lk.ijse.spring.controller;

import lk.ijse.spring.dto.VehicleRentDTO;
import lk.ijse.spring.service.VehicleRentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicleRent")
@CrossOrigin
public class VehicleRentController {

    @Autowired
   VehicleRentService vehicleRentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllVehicleRents() {
        return new ResponseUtil(200,"Ok",vehicleRentService.getAllVehicleRents());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveRent(@ModelAttribute VehicleRentDTO vehicleRentDTO) {
        vehicleRentService.saveVehicleRent(vehicleRentDTO);
        return new ResponseUtil(200,"Ok",null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateRent(@RequestBody VehicleRentDTO vehicleRentDTO) {
        vehicleRentService.updateVehicleRent(vehicleRentDTO);
        return new ResponseUtil(200,"Ok",null);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteRent(@RequestParam String id) {
        vehicleRentService.deleteVehicleRent(id);
        return new ResponseUtil(200,"Ok",null);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchRent(@PathVariable String id) {
        return new ResponseUtil(200,"Ok",vehicleRentService.searchVehicleRent(id));
    }
}
