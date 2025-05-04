package edu.dbms.joinsociety.controller.customer;

import edu.dbms.joinsociety.dto.ProductQuantityDTO;
import edu.dbms.joinsociety.dto.CartDTO;
import edu.dbms.joinsociety.dto.ErrorDTO;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.service.customer.CartService;
import edu.dbms.joinsociety.service.customer.ProductService;
import jakarta.annotation.Resource;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Resource(name = "productService")
    private ProductService productService;

    @Resource(name = "cartService")
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductData(@PathVariable final String id) {
        Optional<ProductDTO> productDTO = productService.getProduct(Long.parseLong(id));
        if (productDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productDTO.get());
        }

        ErrorDTO errorDTO = new ErrorDTO("Product not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @PostMapping("/addToCart")
    public CartDTO addToCart(@RequestBody final ProductQuantityDTO productQuantityDTO) {
        return cartService.addProductToCart(productQuantityDTO);
    }

    @PutMapping("/updateQuantity")
    public CartDTO updateQuantitiesInCart(@RequestBody final ProductQuantityDTO updateCartQuantityDTO) {
        return cartService.updateProductInCart(updateCartQuantityDTO);
    }
}
