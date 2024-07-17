package com.example.drinkdeposit.service;


import com.example.drinkdeposit.exceptions.EntryError;
import com.example.drinkdeposit.model.entities.DrinkConfig;
import com.example.drinkdeposit.repositories.DrinkConfigRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.drinkdeposit.MocksDTO.DrinkConfigDTOMock.drinkConfigDTOMock;
import static com.example.drinkdeposit.MocksDTO.DrinkConfigModel.drinkConfigModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DrinkConfigServiceTest {

    private DrinkConfigService drinkConfigService;
    private DrinkConfigRepository drinkConfigRepository;


    @BeforeEach
    void setUp() {
        this.drinkConfigRepository = mock(DrinkConfigRepository.class);
        this.drinkConfigService = new DrinkConfigService(drinkConfigRepository);
    }

    @Test
    void makePatchingWithSuccesfuly() {
        DrinkConfig mockModel = drinkConfigModel();
        when(drinkConfigRepository.save(any())).thenReturn(mockModel);

        DrinkConfig patchingDrinkConfig = drinkConfigService.patchingDrinkConfig(drinkConfigDTOMock());

        Assertions.assertEquals(mockModel.getMAX_ALCOHOLIC_CAPACITY(), patchingDrinkConfig.getMAX_ALCOHOLIC_CAPACITY());
        Assertions.assertEquals(mockModel.getMAX_NONALCOHOLIC_CAPACITY(), patchingDrinkConfig.getMAX_NONALCOHOLIC_CAPACITY());
        Assertions.assertEquals(mockModel.getPERMIT_SECTION(), patchingDrinkConfig.getPERMIT_SECTION());
    }

    @Test
    void makePatchingWithNoSuccesfuly() {
        when(drinkConfigRepository.save(any())).thenThrow(new EntryError());

        Executable patching =()-> drinkConfigService.patchingDrinkConfig(drinkConfigDTOMock());
        Assertions.assertThrows(EntryError.class, patching);
    }

}
