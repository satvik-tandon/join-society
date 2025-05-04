package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.CustomerRegisterDTO;
import edu.dbms.joinsociety.models.Customer;

import java.time.LocalDateTime;
import java.util.Objects;

public class CustomerDataConvertor {

    public static CustomerDTO toDTO(final Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhoneNumber());
        return customerDTO;
    }

    public static CustomerRegisterDTO toRegisterDTO(CustomerDTO customerDTO) {
        final CustomerRegisterDTO customerRegisterDTO = new CustomerRegisterDTO();
        if (Objects.nonNull(customerDTO.getId())) {
            customerRegisterDTO.setId(customerDTO.getId());
        }
        customerRegisterDTO.setFirstName(customerDTO.getFirstName());
        customerRegisterDTO.setLastName(customerDTO.getLastName());
        customerRegisterDTO.setEmail(customerDTO.getEmail());
        customerRegisterDTO.setPhone(customerDTO.getPhone());
        return customerRegisterDTO;
    }

    public static Customer toDatabaseObject(final CustomerDTO dto) {
        final Customer customer = new Customer();
        populateParentFields(dto, customer);
        if (dto instanceof CustomerRegisterDTO registerDTO) {
            customer.setPasswordHash(registerDTO.getPassword());
        }
        return customer;
    }

    private static void populateParentFields(final CustomerDTO customerDTO, final Customer customer) {
        if (Objects.nonNull(customerDTO.getId())) {
            customer.setId(customerDTO.getId());
        }
        final LocalDateTime currentLocalTime = LocalDateTime.now();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhone());
        customer.setCreatedAt(currentLocalTime);
        customer.setUpdatedAt(currentLocalTime);
    }
}
