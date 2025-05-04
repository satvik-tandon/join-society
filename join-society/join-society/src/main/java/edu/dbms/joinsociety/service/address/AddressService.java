package edu.dbms.joinsociety.service.address;

import edu.dbms.joinsociety.dto.AddressDTO;
import edu.dbms.joinsociety.models.Address;
import edu.dbms.joinsociety.models.Customer;

public interface AddressService {
    Address getOrCreate(AddressDTO address, Customer customer);
}
