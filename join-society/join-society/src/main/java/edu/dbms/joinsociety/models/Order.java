package edu.dbms.joinsociety.models;

import edu.dbms.joinsociety.models.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "order")
@Table(name = "\"order\"")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    @OneToOne
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "order_subtotal", nullable = false, columnDefinition = "FLOAT")
    private Double orderSubtotal;

    @Column(name = "tax_charges", nullable = false, columnDefinition = "FLOAT")
    private Double taxCharges;

    @Column(name = "shipping_fee", nullable = false, columnDefinition = "FLOAT")
    private Double shippingFee = 0.0;

    @Column(name = "total_amount", nullable = false, columnDefinition = "FLOAT")
    private Double totalAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "order")
    private Transaction transaction;

    @OneToMany(mappedBy = "order")
    private List<OrderEntry> orderEntryList;
}
