package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.enums.DrinkType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class DrinkTest {

    @Test
    void makeTestOncheckNegativeValues() {
        Executable drinkTest = () -> new Drink(DrinkType.ALCOHOLIC, "string", 0.0);

        Assertions.assertThrows(IlegalRequest.class, drinkTest);
    }

    @Test
    void makeTestErrorOncheckNegativeValues() {
        Executable drinkTest = () -> new Drink(DrinkType.ALCOHOLIC, "string", 1.0);

        Assertions.assertDoesNotThrow(drinkTest);
    }


}
