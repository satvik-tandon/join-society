package edu.dbms.joinsociety.service.product;


import edu.dbms.joinsociety.models.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> findProductDetailListByIds(List<Long> ids);
    List<ProductDetail> saveAllProductDetails(List<ProductDetail> productDetails);
}
