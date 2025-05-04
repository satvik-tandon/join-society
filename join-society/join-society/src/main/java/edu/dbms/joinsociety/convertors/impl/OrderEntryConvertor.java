package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.ProductInOrderDTO;
import edu.dbms.joinsociety.models.OrderEntry;

public class OrderEntryConvertor {

    public static OrderEntry toDatabaseObject(ProductInOrderDTO dto, ProductInOrderDTO product) {
        final OrderEntry orderEntry = new OrderEntry();
        orderEntry.setPrice(dto.getPrice());
        orderEntry.setQuantity(dto.getQuantity());
        return orderEntry;
    }
}
