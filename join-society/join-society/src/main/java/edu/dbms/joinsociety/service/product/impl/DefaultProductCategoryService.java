package edu.dbms.joinsociety.service.product.impl;

import edu.dbms.joinsociety.convertors.impl.SizeConvertor;
import edu.dbms.joinsociety.dto.SizeDTO;
import edu.dbms.joinsociety.models.ProductCategory;
import edu.dbms.joinsociety.repositories.ProductCategoryRepository;
import edu.dbms.joinsociety.service.product.ProductCategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productCategoryService")
public class DefaultProductCategoryService implements ProductCategoryService {

    @Resource
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<SizeDTO> getSizesAssociatedWithProductCategory(Integer categoryId) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(categoryId);
        return productCategory
                .map(category -> category.getSizes().stream().map(SizeConvertor::toDTO).toList())
                .orElseGet(List::of);
    }
}
