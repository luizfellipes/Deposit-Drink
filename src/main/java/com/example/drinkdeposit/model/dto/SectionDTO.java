package com.example.drinkdeposit.model.dto;

import com.example.drinkdeposit.model.enums.DrinkType;

public record SectionDTO(DrinkType drinkType, String drinkName, Double volume) {
}
