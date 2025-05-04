package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.ProductCategoryDTO;
import edu.dbms.joinsociety.dto.SizeDTO;
import edu.dbms.joinsociety.models.ProductCategory;

import java.util.List;

public class ProductCategoryConvertor {

    public static ProductCategoryDTO toDTO(ProductCategory productCategory) {
        final ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setId(productCategory.getId());
        productCategoryDTO.setName(productCategory.getName());
        productCategoryDTO.setDescription(productCategory.getDescription());

        List<SizeDTO> sizes = productCategory.getSizes().stream()
                .map(SizeConvertor::toDTO)
                .toList();
        productCategoryDTO.setSizes(sizes);

        return productCategoryDTO;
    }

    public static ProductCategory toDatabaseObject(ProductCategoryDTO dto) {
        final ProductCategory productCategory = new ProductCategory();
        productCategory.setName(dto.getName());
        productCategory.setDescription(dto.getDescription());
        return productCategory;
    }
}
