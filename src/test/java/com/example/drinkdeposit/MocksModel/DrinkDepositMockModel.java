package com.example.drinkdeposit.MocksModel;

import com.example.drinkdeposit.model.entities.Drink;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;

import java.time.LocalDateTime;

public abstract class DrinkDepositMockModel {

    public static DrinkDeposit drinkDepositMockModel() {
        return new DrinkDeposit(LocalDateTime.parse("2021-01-01T18:30:00"), "String", "A", MovimentType.ENTRY,
                new Drink(DrinkType.ALCOHOLIC, "Beer", 350.0));

    }

    public static DrinkDeposit drinkDepositMockModelSectionOut() {
        return new DrinkDeposit(LocalDateTime.parse("2021-01-01T18:30:00"), "String", "F", MovimentType.ENTRY,
                new Drink(DrinkType.ALCOHOLIC, "Beer", 3500.0));
    }

    public static DrinkDeposit drinkDepositMockModelSectionOutNonAcloholic() {
        return new DrinkDeposit(LocalDateTime.parse("2021-01-01T18:30:00"), "String", "F", MovimentType.ENTRY,
                new Drink(DrinkType.NONALCOHOLIC, "Beer", 3500.0));
    }


}
