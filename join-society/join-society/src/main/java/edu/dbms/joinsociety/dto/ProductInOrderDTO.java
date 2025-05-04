package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductInOrderDTO {
    private Long productId;
    private Long productDetailId;
    private String productSummary;
    private String size;
    private String color;
    private String imageUrl;
    private Integer quantity;
    private Double price;
}
