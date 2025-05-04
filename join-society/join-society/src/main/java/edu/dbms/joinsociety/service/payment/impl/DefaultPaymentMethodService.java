package edu.dbms.joinsociety.service.payment.impl;

import edu.dbms.joinsociety.convertors.impl.PaymentMethodConvertor;
import edu.dbms.joinsociety.dto.PaymentMethodDTO;
import edu.dbms.joinsociety.models.Customer;
import edu.dbms.joinsociety.models.PaymentMethod;
import edu.dbms.joinsociety.repositories.PaymentMethodRepository;
import edu.dbms.joinsociety.service.payment.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service("paymentMethodService")
public class DefaultPaymentMethodService implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod getOrCreatePaymentMethod(PaymentMethodDTO paymentMethod, Customer customer) {
        Long paymentMethodId = paymentMethod.getId();

        if (Objects.nonNull(paymentMethodId)) {
            Optional<PaymentMethod> foundPaymentMethod = paymentMethodRepository.findById(paymentMethod.getId());
            if (foundPaymentMethod.isPresent()) {
                return foundPaymentMethod.get();
            }

        }

        PaymentMethod newPaymentMethod = PaymentMethodConvertor.toDatabaseObject(paymentMethod);
        newPaymentMethod.setCustomer(customer);
        newPaymentMethod = paymentMethodRepository.save(newPaymentMethod);
        return newPaymentMethod;
    }
}
