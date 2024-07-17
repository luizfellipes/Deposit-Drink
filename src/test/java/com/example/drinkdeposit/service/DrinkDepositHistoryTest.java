package com.example.drinkdeposit.service;

import com.example.drinkdeposit.exceptions.EntryError;
import com.example.drinkdeposit.model.entities.DrinkHistory;
import com.example.drinkdeposit.repositories.DrinkDepositHistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.example.drinkdeposit.MocksModel.DrinkDepositMockModel.drinkDepositMockModel;
import static com.example.drinkdeposit.MocksModel.DrinkHistoryMockModel.drinkHistoryMockModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DrinkDepositHistoryTest {

    private DrinkHistoryService drinkHistoryService;
    private DrinkDepositHistoryRepository drinkDepositHistoryRepository;


    @BeforeEach
    void setUp() {
        this.drinkDepositHistoryRepository = mock(DrinkDepositHistoryRepository.class);
        this.drinkHistoryService = new DrinkHistoryService(drinkDepositHistoryRepository);
    }

    @Test
    void makeASaveWithSucessfully() {
        when(drinkDepositHistoryRepository.save(any())).thenReturn(drinkHistoryMockModel());

        DrinkHistory savedHistory = drinkHistoryService.saveHistory(drinkDepositMockModel());

        Assertions.assertEquals(drinkHistoryMockModel().getData(), savedHistory.getData());
        Assertions.assertEquals(drinkHistoryMockModel().getResponsible(), savedHistory.getResponsible());
        Assertions.assertEquals(drinkHistoryMockModel().getSection(), savedHistory.getSection());
        Assertions.assertEquals(drinkHistoryMockModel().getMovimentType(), savedHistory.getMovimentType());
        Assertions.assertEquals(drinkHistoryMockModel().getDrinkType(), savedHistory.getDrinkType());
        Assertions.assertEquals(drinkHistoryMockModel().getVolume(), savedHistory.getVolume());
        Assertions.assertEquals(drinkHistoryMockModel().getDrinkName(), savedHistory.getDrinkName());
        Assertions.assertEquals(drinkHistoryMockModel().getTotalVolumeInSection(), savedHistory.getTotalVolumeInSection());
    }

    @Test
    void makeErrorOnSaveHistory() {
        when(drinkDepositHistoryRepository.save(any())).thenThrow(new EntryError());

        Executable save = () -> drinkHistoryService.saveHistory(drinkDepositMockModel());

        Assertions.assertThrows(EntryError.class, save);
    }


    @Test
    void makeATestInFindAllWithEmptyList() {
        when(drinkDepositHistoryRepository.findAll()).thenReturn(List.of());

        List<DrinkHistory> histories = drinkHistoryService.getAll();

        Assertions.assertTrue(histories.isEmpty());
    }

    @Test
    void makeATestInFindAllWithAItemInTheList() {
        when(drinkDepositHistoryRepository.findAll()).thenReturn(List.of(drinkHistoryMockModel()));

        List<DrinkHistory> histories = drinkHistoryService.getAll();

        Assertions.assertFalse(histories.isEmpty());
    }


}
