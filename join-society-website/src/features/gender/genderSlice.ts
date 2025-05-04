import { createSlice, PayloadAction } from "@reduxjs/toolkit";

type GenderState = {
    genders: GenderDTO[]
}

const initialState: GenderState = {
    genders: []
};

export const genderSlice = createSlice({
    name: 'gender',
    initialState,
    reducers: {
        setGenders: (state, action: PayloadAction<GenderDTO[]>) => {
            state.genders = action.payload;
        }
    }
});

export const { setGenders } = genderSlice.actions;

export default genderSlice.reducer;