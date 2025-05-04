package edu.dbms.joinsociety.controller.product;

import edu.dbms.joinsociety.dto.SizeDTO;
import edu.dbms.joinsociety.service.product.ProductCategoryService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {

    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;

    @GetMapping("/{categoryId}/sizes")
    public ResponseEntity<Object> getSizesAssociatedWithProductCategory(@PathVariable Integer categoryId) {
        List<SizeDTO> sizes = productCategoryService.getSizesAssociatedWithProductCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(sizes);
    }
}
