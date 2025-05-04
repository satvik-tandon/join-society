package edu.dbms.joinsociety.service.admin.impl;

import edu.dbms.joinsociety.convertors.DataConvertor;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.models.Product;
import edu.dbms.joinsociety.repositories.ProductRepository;
import edu.dbms.joinsociety.service.admin.AdminProductService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminProductService")
public class DefaultAdminProductService implements AdminProductService {

    @Resource(name = "productConvertor")
    private DataConvertor productConvertor;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean createProduct(ProductDTO productDTO) {
        productRepository.save((Product) productConvertor.toDatabaseObject(productDTO));
        return true;
    }
}
