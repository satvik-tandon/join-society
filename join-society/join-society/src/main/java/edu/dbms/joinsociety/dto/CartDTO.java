package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ToString
public class CartDTO {
    private Long cartId;
    private CustomerDTO customer;
    private BigDecimal cartTotal;
    private List<CartEntryDTO> cartEntriesDTO;
}
