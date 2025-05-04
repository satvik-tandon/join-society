package edu.dbms.joinsociety.service.product.impl;

import edu.dbms.joinsociety.models.ProductDetail;
import edu.dbms.joinsociety.repositories.ProductDetailRepository;
import edu.dbms.joinsociety.service.product.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productDetailService")
public class DefaultProductDetailService implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetail> findProductDetailListByIds(List<Long> ids) {
        return productDetailRepository.findAllById(ids);
    }

    @Override
    public List<ProductDetail> saveAllProductDetails(List<ProductDetail> productDetails) {
        return productDetailRepository.saveAll(productDetails);
    }
}
