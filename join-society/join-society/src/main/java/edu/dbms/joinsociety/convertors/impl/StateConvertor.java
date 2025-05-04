package edu.dbms.joinsociety.convertors.impl;

import edu.dbms.joinsociety.dto.StateDTO;
import edu.dbms.joinsociety.models.State;

public class StateConvertor {

    public static StateDTO toDTO(State state) {
        StateDTO dto = new StateDTO();
        dto.setId(state.getId());
        dto.setName(state.getName());
        return dto;
    }

    public static State toDatabaseObject(StateDTO dto) {
        State state = new State();
        if (dto.getId() != null) {
            state.setId(dto.getId());
        }
        state.setName(dto.getName());
        return state;
    }
}
