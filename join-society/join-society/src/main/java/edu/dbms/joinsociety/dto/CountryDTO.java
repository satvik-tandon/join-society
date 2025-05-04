package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CountryDTO {
    private Integer id;
    private String name;
    private String isoCode;
    private List<StateDTO> states;
}
