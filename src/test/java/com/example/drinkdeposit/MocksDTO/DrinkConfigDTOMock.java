package com.example.drinkdeposit.MocksDTO;

import com.example.drinkdeposit.model.dto.DrinkConfigDTO;

import java.util.List;

public abstract class DrinkConfigDTOMock {

    public static DrinkConfigDTO drinkConfigDTOMock() {
        return new DrinkConfigDTO(1, 500.0, 500.0, List.of("A", "B", "C", "D", "E"), false);
    }

    public static DrinkConfigDTO drinkConfigDTOMockNull() {
        return new DrinkConfigDTO(1, null, null, List.of("A", "B", "C", "D", "E"), false);
    }
}
