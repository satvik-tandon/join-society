package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderRequestDTO {
    private CustomerDTO customer;
    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    private PaymentMethodDTO paymentMethod;
    private List<ProductInOrderDTO> products;
    private Double orderSubtotal;
    private Double shippingFee;
    private Double taxCharges;
    private Double totalAmount;
}
