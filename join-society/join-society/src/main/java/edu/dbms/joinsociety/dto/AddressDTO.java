package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {
    private Long id;
    private String line1;
    private String line2;
    private String city;
    private StateDTO state;
    private String postalCode;
}
