package com.example.drinkdeposit.MocksModel;

import com.example.drinkdeposit.model.entities.Drink;
import com.example.drinkdeposit.model.entities.DrinkHistory;
import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;

import java.time.LocalDateTime;

public abstract class DrinkHistoryMockModel {


    public static DrinkHistory drinkHistoryMockModel() {
        return new DrinkHistory(LocalDateTime.parse("2021-01-01T18:30:00"), "String", "A", MovimentType.ENTRY,
                new Drink(DrinkType.ALCOHOLIC, "Beer", 350.0));
    }
}
