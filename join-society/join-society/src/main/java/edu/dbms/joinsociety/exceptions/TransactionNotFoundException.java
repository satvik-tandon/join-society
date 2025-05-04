package edu.dbms.joinsociety.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
