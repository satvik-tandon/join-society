package edu.dbms.joinsociety.service.customer;

import edu.dbms.joinsociety.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<ProductDTO> getProduct(final Long id);
    List<ProductDTO> getTopNProductsCategorizeByGender(Integer N, Integer genderId);
    List<ProductDTO> getProductsByGender(Integer genderId);
}
