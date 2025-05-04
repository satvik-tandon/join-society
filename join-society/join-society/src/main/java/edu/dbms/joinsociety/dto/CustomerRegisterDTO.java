package edu.dbms.joinsociety.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRegisterDTO extends CustomerDTO {
    private String password;
}
