package edu.dbms.joinsociety.controller.customer;

import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.WishlistDTO;
import edu.dbms.joinsociety.dto.WishlistRequestDTO;
import edu.dbms.joinsociety.service.customer.CustomerService;
import edu.dbms.joinsociety.service.customer.WishlistService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class WishlistController {

    @Resource(name = "wishlistService")
    private WishlistService wishlistService;

    @Resource(name = "customerService")
    private CustomerService customerService;

    @GetMapping("/wishlist")
    public WishlistDTO getWishlist(@RequestBody final WishlistRequestDTO requestDTO) {
        final Optional<CustomerDTO> customer = customerService.getCustomer(requestDTO.getCustomerEmail());
        WishlistDTO wishlistDTO = null;
        if (customer.isPresent()) {
            wishlistDTO = wishlistService.getOrCreateWishList(customer.get());
        }
        return wishlistDTO;
    }

    @PutMapping("/addToWishlist")
    public WishlistDTO addToWishlist(@RequestBody final WishlistRequestDTO requestDTO) {
        return wishlistService.addProductToWishlist(requestDTO);
    }
}
