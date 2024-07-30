package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class DrinkConfigTest {


    @Test
    void makeTestOnMaxCapacity() {
        DrinkConfig configTest = new DrinkConfig();

        configTest.maxCapacity(DrinkType.ALCOHOLIC);

        Assertions.assertEquals(configTest.maxCapacity(DrinkType.ALCOHOLIC), 500);
        Assertions.assertEquals(configTest.maxCapacity(DrinkType.NONALCOHOLIC), 400);
        Assertions.assertEquals(configTest.getPERMIT_SECTION(), List.of("A", "B", "C", "D", "E"));
        Assertions.assertFalse(configTest.isDRINK_CAN_BE_TOGETHER());
    }

    @Test
    void makeTestErrorOnMaxCapacity() {
        DrinkConfig configTest = new DrinkConfig();

        configTest.maxCapacity(DrinkType.ALCOHOLIC);

        Assertions.assertNotEquals(configTest.maxCapacity(DrinkType.ALCOHOLIC), 400);
        Assertions.assertNotEquals(configTest.maxCapacity(DrinkType.NONALCOHOLIC), 500);
        Assertions.assertNotEquals(configTest.getPERMIT_SECTION(), List.of("A", "B", "C", "D"));
        Assertions.assertNotEquals(configTest.isDRINK_CAN_BE_TOGETHER(), true);
    }



}
