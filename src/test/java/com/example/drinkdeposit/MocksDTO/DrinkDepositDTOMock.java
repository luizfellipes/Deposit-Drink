package com.example.drinkdeposit.MocksDTO;

import com.example.drinkdeposit.model.dto.DrinkDTO;
import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;

import java.time.LocalDateTime;

public abstract class DrinkDepositDTOMock {

    public static DrinkDepositDTO drinkDepositDtoMockExit() {
        return new DrinkDepositDTO(LocalDateTime.parse("2021-01-01T18:30:00"), MovimentType.EXIT, "employee", "a",
                new DrinkDTO(DrinkType.ALCOHOLIC, "Beer", 350.0));
    }

    public static DrinkDepositDTO drinkDepositDtoMockEntryExcessVolume() {
        return new DrinkDepositDTO(LocalDateTime.parse("2021-01-01T18:30:00"), MovimentType.ENTRY, "employee", "a",
                new DrinkDTO(DrinkType.ALCOHOLIC, "Beer", 3500.0));
    }

    public static DrinkDepositDTO drinkDepositDtoMockEntryAlcoholic() {
        return new DrinkDepositDTO(LocalDateTime.parse("2021-01-01T18:30:00"), MovimentType.ENTRY, "employee", "a",
                new DrinkDTO(DrinkType.ALCOHOLIC, "Beer", 350.0));
    }

    public static DrinkDepositDTO drinkDepositDtoMockEntryNonAlcoholic() {
        return new DrinkDepositDTO(LocalDateTime.parse("2021-01-01T18:30:00"), MovimentType.ENTRY, "employee", "a",
                new DrinkDTO(DrinkType.NONALCOHOLIC, "Beer", 350.0));
    }

    public static DrinkDepositDTO drinkDepositDtoMockSectionOut() {
        return new DrinkDepositDTO(LocalDateTime.parse("2021-01-01T18:30:00"), MovimentType.EXIT, "employee", "F",
                new DrinkDTO(DrinkType.ALCOHOLIC, "Beer", 350.0));
    }


}
