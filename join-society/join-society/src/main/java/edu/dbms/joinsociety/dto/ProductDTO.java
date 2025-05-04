package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class ProductDTO {
    private Long productId;
    private String productSummary;
    private String productDescription;
    private ProductCategoryDTO productCategoryDTO;
    private GenderDTO genderDTO;
    private Double basePrice;
    private Double discountedPrice;
    private Set<ColorDTO> availableColors;
    private List<ProductDetailDTO> productDetailDTO;
}
