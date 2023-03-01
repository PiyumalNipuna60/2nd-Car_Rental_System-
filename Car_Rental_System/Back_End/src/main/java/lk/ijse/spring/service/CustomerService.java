package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO dto);

    void deleteCustomer(String id);

    void updateCustomer(CustomerDTO dto);

    CustomerDTO searchCustomer(String id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO findEmailAndPassword(String email, String password);

    boolean findUser(String email);

    CustomerDTO findNic(String nic);

}
