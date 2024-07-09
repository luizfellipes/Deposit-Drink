package com.example.drinkdeposit.model.dto;

import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record DrinkDepositDTO(
        @NotNull(message = "Insira uma data valida !")
        LocalDateTime data,
        @NotNull(message = "Insira um tipo de movimentação a ser feita.")
        MovimentType movimentType,
        @NotBlank(message = "Insira o responsavel da movimentação.")
        String responsible,
        @NotNull(message = "Insira uma seção valida !")
        String section,
        @Valid
        @NotNull
        DrinkDTO drink) {
}
