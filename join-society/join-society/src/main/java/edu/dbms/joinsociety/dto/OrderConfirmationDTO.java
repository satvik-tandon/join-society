package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderConfirmationDTO {
    private String id;
    private CustomerDTO customer;
    private String orderStatus;
    private AddressDTO shippingAddress;
    private AddressDTO billingAddress;
    private Double orderSubtotal;
    private Double shippingFee;
    private Double taxCharges;
    private Double totalAmount;
    private String createdAt;
    private String transactionId;
    private List<ProductInOrderDTO> products;
}
