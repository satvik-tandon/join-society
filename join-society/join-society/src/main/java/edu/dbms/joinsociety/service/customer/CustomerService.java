package edu.dbms.joinsociety.service.customer;

import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.CustomerLoginDTO;
import edu.dbms.joinsociety.dto.CustomerRegisterDTO;

import java.util.Optional;

public interface CustomerService {
    boolean createCustomer(final CustomerRegisterDTO registerDTO);
    Optional<CustomerDTO> getCustomer(final String customerEmail);
    Optional<CustomerDTO> getCustomerById(final Long id);
    Optional<CustomerDTO> doLogin(final CustomerLoginDTO loginDTO);
}
