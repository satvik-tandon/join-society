package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.SizeDTO;
import edu.dbms.joinsociety.models.Size;

public class SizeConvertor {

    public static SizeDTO toDTO(Size size) {
        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setId(size.getId());
        sizeDTO.setName(size.getName());
        return sizeDTO;
    }

    public static Size toDatabaseObject(SizeDTO dto) {
        Size size = new Size();
        size.setId(dto.getId());
        size.setName(dto.getName());
        return size;
    }
}
