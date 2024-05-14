package com.example.drinkdeposit.model.dto;

import com.example.drinkdeposit.model.enums.DrinkType;

public record DrinkDTO(DrinkType drinkType, String drinkName, int volume) {
}
