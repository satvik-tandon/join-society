package edu.dbms.joinsociety.service.customer.impl;

import edu.dbms.joinsociety.convertors.DataConvertor;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.models.Product;
import edu.dbms.joinsociety.repositories.ProductRepository;
import edu.dbms.joinsociety.service.customer.ProductService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productService")
public class DefaultProductService implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Resource(name = "productConvertor")
    private DataConvertor productConvertor;

    @Override
    public Optional<ProductDTO> getProduct(final Long id) {
        final Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductDTO productDTO = (ProductDTO) productConvertor.toDTO(product.get());
            return Optional.of(productDTO);
        }

        return Optional.empty();
    }

    @Override
    public List<ProductDTO> getTopNProductsCategorizeByGender(Integer N, Integer genderId) {
        List<Product> products = productRepository.findByGenderIdOrderByUpdatedAtDesc(genderId, Limit.of(N));
        return products.stream().map(product -> (ProductDTO) productConvertor.toDTO(product)).toList();
    }

    @Override
    public List<ProductDTO> getProductsByGender(Integer genderId) {
        List<Product> products = productRepository.findByGenderId(genderId);
        return products.stream().map(product -> (ProductDTO) productConvertor.toDTO(product)).toList();
    }
}
