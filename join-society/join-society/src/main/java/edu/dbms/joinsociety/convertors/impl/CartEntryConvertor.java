package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.convertors.DataConvertor;
import edu.dbms.joinsociety.dto.CartEntryDTO;
import edu.dbms.joinsociety.dto.ProductDTO;
import edu.dbms.joinsociety.models.CartEntry;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component("cartEntryConvertor")
public class CartEntryConvertor implements DataConvertor {

    @Resource(name = "productConvertor")
    private DataConvertor productConvertor;

    @Override
    public Object toDTO(Object databaseObject) {
        CartEntry cartEntry = (CartEntry) databaseObject;
        CartEntryDTO cartEntryDTO = new CartEntryDTO();
        cartEntryDTO.setProduct((ProductDTO) productConvertor.toDTO(cartEntry.getProduct()));
        cartEntryDTO.setQuantity(cartEntry.getQuantity());
        return cartEntryDTO;
    }

    @Override
    public Object toDatabaseObject(Object dto) {
        return null;
    }
}
