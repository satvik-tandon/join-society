package edu.dbms.joinsociety.service.customer;

import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.WishlistDTO;
import edu.dbms.joinsociety.dto.WishlistRequestDTO;

public interface WishlistService {
    WishlistDTO getOrCreateWishList(final CustomerDTO customerDTO);

    WishlistDTO createWishlist(final CustomerDTO customerDTO);

    WishlistDTO addProductToWishlist(final WishlistRequestDTO requestDTO);
}
