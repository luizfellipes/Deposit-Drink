package com.example.drinkdeposit.model.dto;

import com.example.drinkdeposit.model.enums.DrinkType;

import java.time.LocalDateTime;

public record DrinkDepositDTO(DrinkType drinkType, String drinkName, Double volume, LocalDateTime data) {
}
