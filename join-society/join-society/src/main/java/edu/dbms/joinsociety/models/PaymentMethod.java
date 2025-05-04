package edu.dbms.joinsociety.models;

import edu.dbms.joinsociety.models.enums.PaymentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "payment_method")
@Data
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @Column(name = "name_on_card", nullable = false)
    private String nameOnCard;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_expiry", nullable = false)
    private String cardExpiry;

    @Column(name = "is_default")
    private Boolean isDefault = Boolean.FALSE;

    public String getLast4DigitsOfCardNumber() {
        if (cardNumber == null || cardNumber.length() <= 4) {
            return cardNumber;
        }
        return cardNumber.substring(cardNumber.length() - 4);
    }
}
