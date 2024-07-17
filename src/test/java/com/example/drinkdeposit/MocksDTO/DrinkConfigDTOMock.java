package com.example.drinkdeposit.MocksDTO;

import com.example.drinkdeposit.model.dto.DrinkConfigDTO;

import java.util.Set;

public abstract class DrinkConfigDTOMock {

    public static DrinkConfigDTO drinkConfigDTO() {
        return new DrinkConfigDTO(1, 500.0, 500.0, Set.of("A", "B", "C", "D", "E"));
    }
}
