package com.example.drinkdeposit.model.dto;

import com.example.drinkdeposit.model.entities.Drink;
import com.example.drinkdeposit.model.enums.MovimentType;

import java.time.LocalDateTime;


public record DrinkDepositDTO(LocalDateTime data, MovimentType movimentType, String responsible, String[] section,
                              Drink drink) {
}
