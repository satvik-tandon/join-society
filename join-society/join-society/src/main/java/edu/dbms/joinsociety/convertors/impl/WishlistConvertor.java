package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.convertors.DataConvertor;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.dto.WishlistDTO;
import edu.dbms.joinsociety.models.Product;
import edu.dbms.joinsociety.models.Wishlist;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component("wishlistConvertor")
public class WishlistConvertor implements DataConvertor {

    @Resource(name = "productConvertor")
    private DataConvertor productDataConvertor;

    @Override
    public Object toDTO(Object databaseObject) {
        final Wishlist wishlist = (Wishlist) databaseObject;
        final WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setCustomer(CustomerDataConvertor.toDTO(wishlist.getCustomer()));
        wishlistDTO.setProducts(new HashSet<>(wishlist.getProducts().size()));
        for (Product product : wishlist.getProducts()) {
            wishlistDTO.getProducts().add((ProductDTO) productDataConvertor.toDTO(product));
        }
        return wishlistDTO;
    }

    @Override
    public Object toDatabaseObject(Object dto) {
        final WishlistDTO wishlistDTO = (WishlistDTO) dto;
        final Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(CustomerDataConvertor.toDatabaseObject(wishlistDTO.getCustomer()));
        wishlist.setProducts(new HashSet<>(wishlistDTO.getProducts().size()));
        for (ProductDTO productDTO : wishlistDTO.getProducts()) {
            wishlist.getProducts().add((Product) productDataConvertor.toDatabaseObject(productDTO));
        }
        return wishlist;
    }
}
