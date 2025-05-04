package edu.dbms.joinsociety.service.order.impl;

import edu.dbms.joinsociety.convertors.impl.CustomerDataConvertor;
import edu.dbms.joinsociety.convertors.impl.OrderConvertor;
import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.CustomerRegisterDTO;
import edu.dbms.joinsociety.dto.OrderConfirmationDTO;
import edu.dbms.joinsociety.dto.OrderRequestDTO;
import edu.dbms.joinsociety.dto.ProductInOrderDTO;
import edu.dbms.joinsociety.exceptions.OrderHistoryFetchException;
import edu.dbms.joinsociety.exceptions.ProductOutOfStockException;
import edu.dbms.joinsociety.exceptions.TransactionNotFoundException;
import edu.dbms.joinsociety.models.Address;
import edu.dbms.joinsociety.models.Customer;
import edu.dbms.joinsociety.models.Order;
import edu.dbms.joinsociety.models.OrderEntry;
import edu.dbms.joinsociety.models.PaymentMethod;
import edu.dbms.joinsociety.models.ProductDetail;
import edu.dbms.joinsociety.models.Transaction;
import edu.dbms.joinsociety.models.enums.OrderStatus;
import edu.dbms.joinsociety.models.enums.TransactionStatus;
import edu.dbms.joinsociety.repositories.CustomerRepository;
import edu.dbms.joinsociety.repositories.OrderEntryRepository;
import edu.dbms.joinsociety.repositories.OrderRepository;
import edu.dbms.joinsociety.repositories.TransactionRepository;
import edu.dbms.joinsociety.service.address.AddressService;
import edu.dbms.joinsociety.service.order.OrderService;
import edu.dbms.joinsociety.service.payment.PaymentMethodService;
import edu.dbms.joinsociety.service.product.ProductDetailService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("orderService")
public class DefaultOrderService implements OrderService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderEntryRepository orderEntryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Resource(name = "addressService")
    private AddressService addressService;

    @Resource(name = "paymentMethodService")
    private PaymentMethodService paymentMethodService;

    @Resource(name = "productDetailService")
    private ProductDetailService productDetailService;

    @Override
    @Transactional(rollbackOn = { ProductOutOfStockException.class, RuntimeException.class })
    public Order createOrder(OrderRequestDTO order) throws ProductOutOfStockException {
        final Customer customer = this.getOrCreateCustomer(order.getCustomer());

        Address shippingAddress = addressService.getOrCreate(order.getShippingAddress(), customer);
        Address billingAddress = addressService.getOrCreate(order.getBillingAddress(), customer);

        PaymentMethod paymentMethod = paymentMethodService.getOrCreatePaymentMethod(order.getPaymentMethod(), customer);

        final Order newOrder = OrderConvertor.toDatabaseObject(order);
        newOrder.setStatus(OrderStatus.CONFIRMED);
        newOrder.setCustomer(customer);
        newOrder.setShippingAddress(shippingAddress);
        newOrder.setBillingAddress(billingAddress);
        orderRepository.save(newOrder);

        Transaction transaction = new Transaction();
        transaction.setOrder(newOrder);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setStatus(TransactionStatus.ON_HOLD);
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        List<Long> productDetailIds = order.getProducts().stream().map(ProductInOrderDTO::getProductDetailId).toList();
        List<ProductDetail> productDetailList = productDetailService.findProductDetailListByIds(productDetailIds);
        Map<Long, ProductDetail> productDetailMap = productDetailList.stream().collect(Collectors.toMap(ProductDetail::getId, Function.identity()));

        List<OrderEntry> orderEntryList = new ArrayList<>();
        for (ProductInOrderDTO product: order.getProducts()) {
            OrderEntry orderEntry = new OrderEntry();
            orderEntry.setOrder(newOrder);

            ProductDetail productDetail = productDetailMap.get(product.getProductDetailId());

            if (productDetail.getInventoryCount() - product.getQuantity() < 0) {
                throw new ProductOutOfStockException("Product out of stock.");
            }

            productDetail.setInventoryCount(productDetail.getInventoryCount() - product.getQuantity());
            orderEntry.setProductDetail(productDetailMap.get(product.getProductDetailId()));
            orderEntry.setPrice(product.getPrice());
            orderEntry.setQuantity(product.getQuantity());
            orderEntryList.add(orderEntry);
        }
                                                                 
        productDetailService.saveAllProductDetails(productDetailMap.values().stream().toList());
        orderEntryRepository.saveAll(orderEntryList);

        return newOrder;
    }

    @Override
    public Optional<OrderConfirmationDTO> getOrderConfirmation(String orderId) throws TransactionNotFoundException {
        System.out.println("Order Id" + orderId);
        Optional<Order> order = orderRepository.findById(orderId);
        List<OrderEntry> orderEntryList = orderEntryRepository.findByOrderId(orderId);

        Optional<Transaction> transaction = transactionRepository.findOneByOrderId(orderId);

        if (transaction.isEmpty()) {
            throw new TransactionNotFoundException("Could not find transaction for the order: " + orderId);
        }

        return order.map(value -> OrderConvertor.toConfirmationDTO(value, transaction.get(), orderEntryList));
    }

    @Override
    public List<OrderConfirmationDTO> getOrderHistory(Long customerId) throws OrderHistoryFetchException {
        if (Objects.isNull(customerId)) {
            throw new OrderHistoryFetchException("You have provided an invalid customer for fetching the order history.");
        }

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new OrderHistoryFetchException("Could not find customer for fetching the order history.");
        }

        List<Order> orders = orderRepository.findOrdersByCustomer(customer.get());
        return orders.stream()
                .map(order -> OrderConvertor.toConfirmationDTO(order, null, null))
                .toList();
    }

    private Customer getOrCreateCustomer(CustomerDTO customer) {
        Long customerId = customer.getId();

        if (Objects.nonNull(customerId)) {
            Optional<Customer> foundCustomer = customerRepository.findById(customerId);
            if (foundCustomer.isPresent()) {
                return foundCustomer.get();
            }
        }

        CustomerRegisterDTO customerRegisterDTO = CustomerDataConvertor.toRegisterDTO(customer);
        customerRegisterDTO.setPassword("testing123"); // set default password (for now)
        Customer newCustomer = CustomerDataConvertor.toDatabaseObject(customerRegisterDTO);
        newCustomer = customerRepository.save(newCustomer);

        return newCustomer;
    }
}
