package com.example.drinkdeposit.model.dto;


import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DrinkConfigDTO(
        @NotNull(message = "É necessario passar um id para realizar a atualização !")
        Integer id,
        Double MAX_ALCOHOLIC_CAPACITY,
        Double MAX_NONALCOHOLIC_CAPACITY,
        List<String> PERMIT_SECTION,
        boolean DRINK_CAN_BE_TOGETHER){
}
