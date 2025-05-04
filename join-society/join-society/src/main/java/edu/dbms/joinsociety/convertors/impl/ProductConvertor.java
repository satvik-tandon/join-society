package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.convertors.DataConvertor;
import edu.dbms.joinsociety.dto.ColorDTO;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.dto.ProductDetailDTO;
import edu.dbms.joinsociety.models.Product;
import edu.dbms.joinsociety.models.ProductDetail;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("productConvertor")
public class ProductConvertor implements DataConvertor {

    @Override
    public Object toDTO(Object databaseObject) {
        final Product product = (Product) databaseObject;
        final ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getId());
        productDTO.setProductSummary(product.getName());
        productDTO.setProductDescription(product.getDescription());

        productDTO.setProductCategoryDTO(ProductCategoryConvertor.toDTO(product.getCategory()));
        productDTO.setGenderDTO(GenderConvertor.toDTO(product.getGender()));

        productDTO.setBasePrice(product.getBasePrice());
        productDTO.setDiscountedPrice(product.getDiscountedPrice());

        Set<ColorDTO> colors = product.getProductDetails().stream()
                .map(ProductDetail::getColor)
                .map(ProductColorConvertor::toDTO)
                .collect(Collectors.toSet());

        productDTO.setAvailableColors(colors);

        List<ProductDetailDTO> productDetails = product.getProductDetails().stream()
                .map(ProductDetailsConvertor::toDTO)
                .toList();
        productDTO.setProductDetailDTO(productDetails);

        return productDTO;
    }

    @Override
    public Object toDatabaseObject(Object dto) {
        final Product product = new Product();
        final ProductDTO productDTO = (ProductDTO) dto;

        product.setName(productDTO.getProductSummary());
        product.setDescription(productDTO.getProductDescription());
        product.setCategory(ProductCategoryConvertor.toDatabaseObject(productDTO.getProductCategoryDTO()));
        product.setGender(GenderConvertor.toDatabaseObject(productDTO.getGenderDTO()));

        product.setBasePrice(productDTO.getBasePrice());
        product.setDiscountedPrice(productDTO.getDiscountedPrice());

        List<ProductDetail> productDetails = productDTO.getProductDetailDTO().stream()
                .map(ProductDetailsConvertor::toDatabaseObject)
                .toList();
        product.setProductDetails(productDetails);

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }
}
