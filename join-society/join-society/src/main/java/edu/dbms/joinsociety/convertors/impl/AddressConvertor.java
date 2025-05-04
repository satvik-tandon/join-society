package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.AddressDTO;
import edu.dbms.joinsociety.models.Address;

public class AddressConvertor {

    public static AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setLine1(address.getLine1());
        dto.setLine2(address.getLine2());
        dto.setCity(address.getCity());
        dto.setState(StateConvertor.toDTO(address.getState()));
        dto.setPostalCode(address.getPostalCode());
        return dto;
    }

    public static Address toDatabaseObject(AddressDTO dto) {
        Address address = new Address();
        if (dto.getId() != null) {
            address.setId(dto.getId());
        }
        address.setLine1(dto.getLine1());
        address.setLine2(dto.getLine2());
        address.setCity(dto.getCity());
        address.setState(StateConvertor.toDatabaseObject(dto.getState()));
        address.setPostalCode(dto.getPostalCode());
        return address;
    }
}
