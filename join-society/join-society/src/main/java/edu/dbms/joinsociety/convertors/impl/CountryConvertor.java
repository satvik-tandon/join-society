package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.CountryDTO;
import edu.dbms.joinsociety.dto.StateDTO;
import edu.dbms.joinsociety.models.Country;

import java.util.List;

public class CountryConvertor {

    public static CountryDTO toDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setIsoCode(country.getIsoCode());
        List<StateDTO> states = country.getStates().stream().map(StateConvertor::toDTO).toList();
        dto.setStates(states);
        return dto;
    }

    public static Country toDatabaseObject(CountryDTO dto) {
        Country country = new Country();
        if (dto.getId() != null) {
            country.setId(dto.getId());
        }
        country.setName(dto.getName());
        country.setIsoCode(dto.getIsoCode());
        return country;
    }
}
