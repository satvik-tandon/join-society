package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.ProductDetailDTO;
import edu.dbms.joinsociety.models.ProductDetail;

public class ProductDetailsConvertor {

    public static ProductDetailDTO toDTO(ProductDetail productDetail) {
        final ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(productDetail.getId());
        productDetailDTO.setSize(SizeConvertor.toDTO(productDetail.getSize()));
        productDetailDTO.setColor(ProductColorConvertor.toDTO(productDetail.getColor()));
        productDetailDTO.setImageUrl(productDetail.getImageUrl());
        productDetailDTO.setInventoryCount(productDetail.getInventoryCount());
        return productDetailDTO;
    }

    public static ProductDetail toDatabaseObject(ProductDetailDTO dto) {
        final ProductDetail productDetail = new ProductDetail();
        if (dto.getId() != null) {
            productDetail.setId(dto.getId());
        }
        productDetail.setSize(SizeConvertor.toDatabaseObject(dto.getSize()));
        productDetail.setColor(ProductColorConvertor.toDatabaseObject(dto.getColor()));
        productDetail.setImageUrl(dto.getImageUrl());
        productDetail.setInventoryCount(dto.getInventoryCount());
        return productDetail;
    }
}
