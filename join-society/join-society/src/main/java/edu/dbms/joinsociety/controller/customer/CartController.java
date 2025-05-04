package edu.dbms.joinsociety.controller.customer;

import edu.dbms.joinsociety.dto.CartDTO;
import edu.dbms.joinsociety.service.customer.CartService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {


    @Resource(name = "cartService")
    private CartService cartService;

    @GetMapping("/cart")
    public CartDTO getCartForUser() {
        return cartService.getOrCreateCart();
    }
}
