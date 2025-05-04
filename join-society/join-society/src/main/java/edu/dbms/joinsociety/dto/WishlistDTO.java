package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class WishlistDTO {
    private CustomerDTO customer;
    private Set<ProductDTO> products;
}
