package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.ColorDTO;
import edu.dbms.joinsociety.models.Color;

public class ProductColorConvertor {

    public static ColorDTO toDTO(Color color) {
        final ColorDTO colorDTO = new ColorDTO();
        colorDTO.setId(color.getId());
        colorDTO.setName(color.getName());
        colorDTO.setHexCode(color.getHexCode());
        return colorDTO;
    }


    public static Color toDatabaseObject(ColorDTO dto) {
        final Color color = new Color();
        if (dto.getId() != null) {
            color.setId(dto.getId());
        }
        color.setName(dto.getName());
        color.setHexCode(dto.getHexCode());
        return color;
    }
}
