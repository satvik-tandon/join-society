package edu.dbms.joinsociety.service.customer;

import edu.dbms.joinsociety.dto.ProductQuantityDTO;
import edu.dbms.joinsociety.dto.CartDTO;

public interface CartService {
    CartDTO getOrCreateCart();

    CartDTO addProductToCart(final ProductQuantityDTO productQuantityDTO);

    CartDTO updateProductInCart(final ProductQuantityDTO updateCartQuantityDTO);

    CartDTO mergeAnonymousCartWithCustomerCart();
}
