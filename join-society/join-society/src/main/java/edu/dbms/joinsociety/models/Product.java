package edu.dbms.joinsociety.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategory category;

    @OneToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private Gender gender;

    @Column(name = "base_price", nullable = false, columnDefinition = "FLOAT")
    private Double basePrice;

    @Column(name = "discounted_price", columnDefinition = "FLOAT")
    private Double discountedPrice;

    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;

    public Double getSellingPrice() {
        if (discountedPrice == null || discountedPrice == 0.0) {
            return this.basePrice;
        }

        return Math.min(discountedPrice, basePrice);
    }
}
