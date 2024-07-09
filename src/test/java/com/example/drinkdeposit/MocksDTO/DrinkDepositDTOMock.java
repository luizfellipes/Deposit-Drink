package com.example.drinkdeposit.MocksDTO;

import com.example.drinkdeposit.model.dto.DrinkDTO;
import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;

import java.time.LocalDateTime;

public abstract class DrinkDepositDTOMock {

    public static DrinkDepositDTO drinkDepositDtoMock() {
        return new DrinkDepositDTO(LocalDateTime.parse("2021-01-01T18:30:00"), MovimentType.EXIT, "employee", "a",
                new DrinkDTO(DrinkType.ALCOHOLIC, "Beer", 350.0));
    }


}
