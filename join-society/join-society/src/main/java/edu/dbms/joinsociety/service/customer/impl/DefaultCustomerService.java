package edu.dbms.joinsociety.service.customer.impl;

import edu.dbms.joinsociety.convertors.impl.CustomerDataConvertor;
import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.CustomerLoginDTO;
import edu.dbms.joinsociety.dto.CustomerRegisterDTO;
import edu.dbms.joinsociety.helper.PasswordHelper;
import edu.dbms.joinsociety.models.Customer;
import edu.dbms.joinsociety.repositories.CustomerRepository;
import edu.dbms.joinsociety.service.customer.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("customerService")
public class DefaultCustomerService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HttpSession httpSession;

    @Override
    public boolean createCustomer(final CustomerRegisterDTO registerDTO) {
        boolean success = false;
        registerDTO.setPassword(PasswordHelper.hashPasswordText(registerDTO.getPassword()));
        final Customer customer = CustomerDataConvertor.toDatabaseObject(registerDTO);
        try {
            customerRepository.save(customer);
            success = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return success;
    }

    @Override
    public Optional<CustomerDTO> getCustomer(String customerEmail) {
        final Optional<Customer> customer = customerRepository.findOneByEmail(customerEmail);
        return customer.map(CustomerDataConvertor::toDTO);
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(final Long id) {
        final Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(CustomerDataConvertor::toDTO);
    }

    @Override
    public Optional<CustomerDTO> doLogin(final CustomerLoginDTO loginDTO) {
        final Optional<Customer> customer = customerRepository.findOneByEmail(loginDTO.getEmail());
        if (customer.isPresent() && isCorrectPassword(customer.get(), loginDTO.getPassword())) {
            CustomerDTO customerDTO = CustomerDataConvertor.toDTO(customer.get());
            httpSession.setAttribute("customer", customerDTO);
            return Optional.of(customerDTO);
        }
        return Optional.empty();
    }

    private boolean isCorrectPassword(final Customer customer, final String customerPassword) {
        final String hashedPasswordInput = PasswordHelper.hashPasswordText(customerPassword);
        return hashedPasswordInput.equals(customer.getPasswordHash());
    }
}
