package edu.dbms.joinsociety.service.country.impl;

import edu.dbms.joinsociety.convertors.impl.CountryConvertor;
import edu.dbms.joinsociety.dto.CountryDTO;
import edu.dbms.joinsociety.models.Country;
import edu.dbms.joinsociety.repositories.CountryRepository;
import edu.dbms.joinsociety.service.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("countryService")
public class DefaultCountryService implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryDTO> getCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(CountryConvertor::toDTO).toList();
    }
}
