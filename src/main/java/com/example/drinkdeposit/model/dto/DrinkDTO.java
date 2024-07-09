package com.example.drinkdeposit.model.dto;

import com.example.drinkdeposit.model.enums.DrinkType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DrinkDTO(
        @NotNull(message = "Insira o tipo de bebida !")
        DrinkType drinkType,
        @NotBlank(message = "Insira o nome da bebida !")
        String drinkName,
        @NotNull(message = "Insira o volume desejado !")
        Double volume) {
}
