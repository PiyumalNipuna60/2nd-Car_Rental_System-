package lk.ijse.spring.controller;

import lk.ijse.spring.dto.MaintenanceDTO;
import lk.ijse.spring.service.MaintenanceService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/maintenance")
@CrossOrigin
public class MaintenanceController {

    @Autowired
    MaintenanceService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllMaintenance() {
        return new ResponseUtil(200, "Ok", service.getAllMaintenances());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil addMaintenance(MaintenanceDTO dto) {
        service.addMaintenance(dto);
        return new ResponseUtil(200, "Saved", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateMaintenance(@RequestBody MaintenanceDTO dto) {
        service.updateMaintenance(dto);
        return new ResponseUtil(200, "Updated", null);
    }

    @DeleteMapping(params = {"maintenanceId"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteMaintenance(@RequestParam String maintenanceId) {
        service.deleteMaintenance(maintenanceId);
        return new ResponseUtil(200, "Deleted", null);
    }

    @GetMapping(path = "/{maintenanceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchMaintenance(@PathVariable String maintenanceId) {
        return new ResponseUtil(200, "Ok", service.searchMaintenance(maintenanceId));
    }

    @GetMapping(path = "/generateMaintenanceId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateMaintenanceId() {
        return new ResponseUtil(200, "Ok", service.generateMaintenanceId());
    }

    @PutMapping(path = "/{maintenanceId}/{cost}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateMaintenanceCost(@PathVariable String maintenanceId, @PathVariable double cost) {
        service.updateMaintenanceCost(maintenanceId, cost);
        return new ResponseUtil(200, "Updated", null);
    }

    @GetMapping(path = "/underMaintenances", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllUnderMaintenances() {
        return new ResponseUtil(200, "Ok", service.getAllUnderMaintenances());
    }
}
