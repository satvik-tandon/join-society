package edu.dbms.joinsociety.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOutOfStockException extends Exception {
    public ProductOutOfStockException(String message) {
        super(message);
    }
}
