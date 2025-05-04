package edu.dbms.joinsociety.service.address.impl;

import edu.dbms.joinsociety.convertors.impl.AddressConvertor;
import edu.dbms.joinsociety.dto.AddressDTO;
import edu.dbms.joinsociety.models.Address;
import edu.dbms.joinsociety.models.Customer;
import edu.dbms.joinsociety.repositories.AddressRepository;
import edu.dbms.joinsociety.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service("addressService")
public class DefaultAddressService implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address getOrCreate(AddressDTO address, Customer customer) {
        Long addressId = address.getId();
        if (Objects.nonNull(addressId)) {
            Optional<Address> foundAddress = addressRepository.findById(address.getId());
            if (foundAddress.isPresent()) {
                return foundAddress.get();
            }
        }

        Address newAddress = AddressConvertor.toDatabaseObject(address);
        newAddress.setCustomer(customer);
        newAddress = addressRepository.save(newAddress);
        return newAddress;
    }
}
