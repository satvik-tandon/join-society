package edu.dbms.joinsociety.controller.admin;

import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.service.admin.AdminProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminProductService")
    private AdminProductService adminProductService;

    @PostMapping("/product/create")
    public boolean createProduct(@RequestBody ProductDTO productDTO) {
        adminProductService.createProduct(productDTO);
        return false;
    }
    // API: /product/update
    // API: /product/delete

    //Todo Add Promotions Data

}
