package edu.dbms.joinsociety.service.payment;

import edu.dbms.joinsociety.dto.PaymentMethodDTO;
import edu.dbms.joinsociety.models.Customer;
import edu.dbms.joinsociety.models.PaymentMethod;

public interface PaymentMethodService {
    PaymentMethod getOrCreatePaymentMethod(PaymentMethodDTO paymentMethod, Customer customer);
}
