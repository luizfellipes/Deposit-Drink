package com.example.drinkdeposit.MocksDTO;

import com.example.drinkdeposit.model.entities.DrinkConfig;

public abstract class DrinkConfigModel {

    public static DrinkConfig drinkConfigModel(){
        return new DrinkConfig();
    }
}
