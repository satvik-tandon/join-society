package edu.dbms.joinsociety.controller.product;

import edu.dbms.joinsociety.dto.ErrorDTO;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.service.customer.ProductService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final static Integer MEN_GENDER_ID = 1;
    private final static Integer WOMEN_GENDER_ID = 2;
    private final static Integer NEW_ARRIVALS_LIMIT_PER_GENDER = 3;

    @Resource(name = "productService")
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getProducts(@RequestParam Optional<Integer> genderId) {
        List<ProductDTO> result = new ArrayList<>();

        if (genderId.isEmpty()) {
            List<ProductDTO> menNewArrivals = productService.getTopNProductsCategorizeByGender(NEW_ARRIVALS_LIMIT_PER_GENDER, MEN_GENDER_ID);
            List<ProductDTO> womenNewArrivals = productService.getTopNProductsCategorizeByGender(NEW_ARRIVALS_LIMIT_PER_GENDER, WOMEN_GENDER_ID);
            result.addAll(Stream.concat(menNewArrivals.stream(), womenNewArrivals.stream()).toList());
        } else {
            List<ProductDTO> products = productService.getProductsByGender(genderId.get());
            result.addAll(products);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable Long id) {
        Optional<ProductDTO> product = productService.getProduct(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        }

        ErrorDTO error = new ErrorDTO("Product not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
