package edu.dbms.joinsociety.service.customer.impl;

import edu.dbms.joinsociety.convertors.DataConvertor;
import edu.dbms.joinsociety.convertors.impl.CustomerDataConvertor;
import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.dto.WishlistDTO;
import edu.dbms.joinsociety.dto.WishlistRequestDTO;
import edu.dbms.joinsociety.models.Customer;
import edu.dbms.joinsociety.models.Wishlist;
import edu.dbms.joinsociety.repositories.WishlistRepository;
import edu.dbms.joinsociety.service.customer.CustomerService;
import edu.dbms.joinsociety.service.customer.ProductService;
import edu.dbms.joinsociety.service.customer.WishlistService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@Service("wishlistService")
public class DefaultWishlistService implements WishlistService {

    @Resource(name = "wishlistRepository")
    private WishlistRepository wishlistRepository;

    @Resource(name = "wishlistConvertor")
    private DataConvertor wishlistConvertor;

    @Resource(name = "customerService")
    private CustomerService customerService;

    @Resource(name = "productService")
    private ProductService productService;

    @Override
    public WishlistDTO getOrCreateWishList(final CustomerDTO customerDTO) {
        final Customer customer = CustomerDataConvertor.toDatabaseObject(customerDTO);
        final Wishlist wishlist = wishlistRepository.getWishlistByCustomer(customer);
        WishlistDTO wishlistDTO = null;
        if (Objects.isNull(wishlist)) {
            wishlistDTO = createWishlist(customerDTO);
        } else {
            wishlistDTO = (WishlistDTO) wishlistConvertor.toDTO(wishlist);
        }
        return wishlistDTO;
    }

    @Override
    public WishlistDTO createWishlist(final CustomerDTO customerDTO) {
        final Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(CustomerDataConvertor.toDatabaseObject(customerDTO));
        wishlist.setProducts(new HashSet<>());
        wishlistRepository.save(wishlist);
        return (WishlistDTO) wishlistConvertor.toDTO(wishlist);
    }

    @Override
    public WishlistDTO addProductToWishlist(final WishlistRequestDTO requestDTO) {
        final Optional<CustomerDTO> customer = customerService.getCustomer(requestDTO.getCustomerEmail());
        WishlistDTO wishlistDTO = null;
        if (customer.isPresent()) {
            wishlistDTO = getOrCreateWishList(customer.get());
            Optional<ProductDTO> product = productService.getProduct(Long.valueOf(requestDTO.getProductId()));
            if (product.isPresent()) {
                wishlistDTO.getProducts().add(product.get());
            }
        }

        saveWishlist(wishlistDTO);
        return wishlistDTO;
    }

    private void saveWishlist(final WishlistDTO wishlistDTO) {
        wishlistRepository.save((Wishlist) wishlistConvertor.toDatabaseObject(wishlistDTO));
    }

}
