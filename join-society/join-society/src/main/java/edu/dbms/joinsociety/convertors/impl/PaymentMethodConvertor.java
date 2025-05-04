package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.PaymentMethodDTO;
import edu.dbms.joinsociety.models.PaymentMethod;
import edu.dbms.joinsociety.models.enums.PaymentType;

public class PaymentMethodConvertor {

    public static PaymentMethodDTO toDTO(PaymentMethod paymentMethod) {
        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setId(paymentMethod.getId());
        dto.setPaymentType(paymentMethod.getPaymentType().name());
        dto.setNameOnCard(paymentMethod.getNameOnCard());
        dto.setCardNumber(paymentMethod.getLast4DigitsOfCardNumber());
        return dto;
    }

    public static PaymentMethod toDatabaseObject(PaymentMethodDTO dto) {
        PaymentMethod paymentMethod = new PaymentMethod();
        if (dto.getId() != null) {
            paymentMethod.setId(dto.getId());
        }
        paymentMethod.setPaymentType(PaymentType.valueOf(dto.getPaymentType()));
        paymentMethod.setNameOnCard(dto.getNameOnCard());
        paymentMethod.setCardNumber(dto.getCardNumber());
        paymentMethod.setCardExpiry(dto.getCardExpiry());
        return paymentMethod;
    }
}
