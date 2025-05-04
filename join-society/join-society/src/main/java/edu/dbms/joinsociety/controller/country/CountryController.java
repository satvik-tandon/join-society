package edu.dbms.joinsociety.controller.country;

import edu.dbms.joinsociety.dto.CountryDTO;
import edu.dbms.joinsociety.service.country.CountryService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Resource(name = "countryService")
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<Object> getCountries() {
        List<CountryDTO> countries = countryService.getCountries();
        return ResponseEntity.status(HttpStatus.OK).body(countries);
    }
}
