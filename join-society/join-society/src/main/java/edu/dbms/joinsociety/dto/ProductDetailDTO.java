package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductDetailDTO {
    private Long id;
    private SizeDTO size;
    private ColorDTO color;
    private String imageUrl;
    private Integer inventoryCount;
}
