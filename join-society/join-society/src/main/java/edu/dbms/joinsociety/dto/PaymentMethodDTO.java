package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentMethodDTO {
    private Long id;
    private String paymentType;
    private String nameOnCard;
    private String cardNumber;
    private String cardExpiry;
}
