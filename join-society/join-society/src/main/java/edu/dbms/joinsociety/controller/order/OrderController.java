package edu.dbms.joinsociety.controller.order;

import edu.dbms.joinsociety.dto.ErrorDTO;
import edu.dbms.joinsociety.dto.OrderConfirmationDTO;
import edu.dbms.joinsociety.dto.OrderRequestDTO;
import edu.dbms.joinsociety.exceptions.OrderHistoryFetchException;
import edu.dbms.joinsociety.exceptions.ProductOutOfStockException;
import edu.dbms.joinsociety.exceptions.TransactionNotFoundException;
import edu.dbms.joinsociety.models.Order;
import edu.dbms.joinsociety.service.order.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource(name = "orderService")
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequestDTO order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            Optional<OrderConfirmationDTO> orderConfirmation = orderService.getOrderConfirmation(createdOrder.getId());
            return ResponseEntity.status(HttpStatus.OK).body(orderConfirmation);

        } catch (ProductOutOfStockException e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (TransactionNotFoundException e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrder(@PathVariable String orderId) {
        try {
            Optional<OrderConfirmationDTO> orderConfirmation = orderService.getOrderConfirmation(orderId);
            if (orderConfirmation.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(orderConfirmation);
            }

            ErrorDTO error = new ErrorDTO("Order not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (TransactionNotFoundException e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/history/{customerId}")
    public ResponseEntity<Object> getOrderHistoryOfCustomer(@PathVariable Long customerId) {
        try {
            List<OrderConfirmationDTO> orders = orderService.getOrderHistory(customerId);
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        } catch (OrderHistoryFetchException e) {
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
