package edu.dbms.joinsociety.service.order;

import edu.dbms.joinsociety.dto.OrderConfirmationDTO;
import edu.dbms.joinsociety.dto.OrderRequestDTO;
import edu.dbms.joinsociety.exceptions.OrderHistoryFetchException;
import edu.dbms.joinsociety.exceptions.ProductOutOfStockException;
import edu.dbms.joinsociety.exceptions.TransactionNotFoundException;
import edu.dbms.joinsociety.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(OrderRequestDTO order) throws ProductOutOfStockException;
    Optional<OrderConfirmationDTO> getOrderConfirmation(String orderId) throws TransactionNotFoundException;
    List<OrderConfirmationDTO> getOrderHistory(Long customerId) throws OrderHistoryFetchException;
}
