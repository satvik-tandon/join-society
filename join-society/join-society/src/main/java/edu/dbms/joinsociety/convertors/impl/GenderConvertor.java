package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.GenderDTO;
import edu.dbms.joinsociety.models.Gender;

public class GenderConvertor {

    public static GenderDTO toDTO(Gender gender) {
        final GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(gender.getId());
        genderDTO.setName(gender.getName());
        return genderDTO;
    }

    public static Gender toDatabaseObject(GenderDTO dto) {
        final Gender gender = new Gender();
        gender.setId(dto.getId());
        gender.setName(dto.getName());
        return gender;
    }
}
