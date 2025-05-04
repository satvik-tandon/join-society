package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.OrderConfirmationDTO;
import edu.dbms.joinsociety.dto.OrderRequestDTO;
import edu.dbms.joinsociety.dto.ProductInOrderDTO;
import edu.dbms.joinsociety.models.Order;
import edu.dbms.joinsociety.models.OrderEntry;
import edu.dbms.joinsociety.models.ProductDetail;
import edu.dbms.joinsociety.models.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderConvertor {

    public static OrderConfirmationDTO toConfirmationDTO(Order order, Transaction transaction, List<OrderEntry> orderEntryList) {
        OrderConfirmationDTO dto = new OrderConfirmationDTO();
        dto.setId(order.getId());
        dto.setCustomer(CustomerDataConvertor.toDTO(order.getCustomer()));
        dto.setOrderStatus(order.getStatus().name());
        dto.setShippingAddress(AddressConvertor.toDTO(order.getShippingAddress()));
        dto.setBillingAddress(AddressConvertor.toDTO(order.getBillingAddress()));
        dto.setOrderSubtotal(order.getOrderSubtotal());
        dto.setShippingFee(order.getShippingFee());
        dto.setTaxCharges(order.getTaxCharges());
        dto.setTotalAmount(order.getTotalAmount());
        if (Objects.nonNull(transaction)) {
            dto.setTransactionId(transaction.getId());
        }
        dto.setCreatedAt(order.getCreatedAt().toString());

        if (Objects.nonNull(orderEntryList)) {
            List<ProductInOrderDTO> products = orderEntryList.stream().map(OrderConvertor::toProductInOrderDTO).toList();
            dto.setProducts(products);
        }

        return dto;
    }

    public static ProductInOrderDTO toProductInOrderDTO(OrderEntry orderEntry) {
        ProductInOrderDTO dto = new ProductInOrderDTO();
        ProductDetail productDetail = orderEntry.getProductDetail();
        dto.setProductId(productDetail.getProduct().getId());
        dto.setProductDetailId(productDetail.getId());
        dto.setProductSummary(productDetail.getProduct().getName());
        dto.setSize(productDetail.getSize().getName());
        dto.setColor(productDetail.getColor().getName());
        dto.setImageUrl(productDetail.getImageUrl());
        dto.setQuantity(orderEntry.getQuantity());
        dto.setPrice(orderEntry.getPrice());
        return dto;
    }

    public static Order toDatabaseObject(OrderRequestDTO dto) {
        final Order order = new Order();
        order.setOrderSubtotal(dto.getOrderSubtotal());
        order.setShippingFee(dto.getShippingFee());
        order.setTaxCharges(dto.getTaxCharges());
        order.setTotalAmount(dto.getTotalAmount());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }
}
