package lk.ijse.spring.controller;

import lk.ijse.spring.dto.AdminDTO;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllAdmins() {
        return new ResponseUtil(200, "Ok", service.getAllAdmins());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveAdmin(AdminDTO dto) {
        service.saveAdmin(dto);
        return new ResponseUtil(200, "Saved", null);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateAdmin(@RequestBody AdminDTO dto) {
        service.updateAdmin(dto);
        return new ResponseUtil(200, "Updated", null);
    }

    @DeleteMapping(params = {"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteAdmin(@RequestParam String id) {
        service.deleteAdmin(id);
        return new ResponseUtil(200, "Deleted", null);
    }

    @GetMapping(path = "/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchAdminByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        if (service.findAdminByUserName(username)) {
            if (service.findAdminByPassWord(password)) {
                return new ResponseUtil(200, "Login Successful", null);
            } else {
                return new ResponseUtil(404, "Incorrect Password", null);
            }
        } else {
            return new ResponseUtil(404, "Incorrect Username", null);
        }
    }

    @GetMapping(path = "/generateAdminID")
    public ResponseUtil generateAdminId() {
        return new ResponseUtil(200, "Ok", service.generateAdminId());
    }
}
