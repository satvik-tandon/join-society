package edu.dbms.joinsociety.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class ProductCategoryDTO {
    private Integer id;
    private String name;
    private String description;
    private List<SizeDTO> sizes;
}
