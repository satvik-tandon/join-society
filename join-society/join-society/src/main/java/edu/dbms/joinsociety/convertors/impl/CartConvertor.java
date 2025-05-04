package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.convertors.DataConvertor;
import edu.dbms.joinsociety.dto.CartDTO;
import edu.dbms.joinsociety.dto.CartEntryDTO;
import edu.dbms.joinsociety.models.Cart;
import edu.dbms.joinsociety.models.CartEntry;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

@Component("cartConvertor")
public class CartConvertor implements DataConvertor {

    @Resource(name = "cartEntryConvertor")
    private DataConvertor cartEntryConvertor;

    @Override
    public Object toDTO(Object databaseObject) {
        final Cart cart = (Cart) databaseObject;
        final CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getId());
        if (Objects.nonNull(cart.getCustomer())) {
            cartDTO.setCustomer(CustomerDataConvertor.toDTO(cart.getCustomer()));
        }
        cartDTO.setCartTotal(BigDecimal.valueOf(cart.getCartTotal()));
        cartDTO.setCartEntriesDTO(new ArrayList<>());
        if (CollectionUtils.isNotEmpty(cart.getCartEntries())) {
            for (CartEntry cartEntry : cart.getCartEntries()) {
                cartDTO.getCartEntriesDTO().add((CartEntryDTO) cartEntryConvertor.toDTO(cartEntry));
            }
        }
        return cartDTO;
    }

    @Override
    public Object toDatabaseObject(Object dto) {
        return null;
    }
}
