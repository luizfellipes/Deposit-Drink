package com.example.drinkdeposit.service;

import com.example.drinkdeposit.exceptions.EntryError;
import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.repositories.DrinkDepositHistoryRepository;
import com.example.drinkdeposit.repositories.DrinkDepositRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static com.example.drinkdeposit.MocksDTO.DrinkDepositDTOMock.drinkDepositDtoMockEntry;
import static com.example.drinkdeposit.MocksDTO.DrinkDepositDTOMock.drinkDepositDtoMockExit;
import static com.example.drinkdeposit.MocksModel.DrinkConfigMockModel.drinkConfigModel;
import static com.example.drinkdeposit.MocksModel.DrinkDepositMockModel.drinkDepositMockModel;
import static com.example.drinkdeposit.MocksModel.DrinkDepositMockModel.drinkDepositMockModelSectionOut;
import static com.example.drinkdeposit.MocksModel.DrinkHistoryMockModel.drinkHistoryMockModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DrinkDepositServiceTest {

    private DrinkDepositService drinkDepositService;
    private DrinkDepositRepository drinkDepositRepository;
    private DrinkHistoryService drinkHistoryService;
    private DrinkDepositHistoryRepository drinkDepositHistoryRepository;

    @BeforeEach
    void setUp() {
        this.drinkDepositRepository = mock(DrinkDepositRepository.class);
        this.drinkDepositHistoryRepository = mock(DrinkDepositHistoryRepository.class);
        this.drinkHistoryService = new DrinkHistoryService(drinkDepositHistoryRepository);
        this.drinkDepositService = new DrinkDepositService(drinkDepositRepository, drinkHistoryService, drinkConfigModel());
    }

    @Test
    void makeASaveWithSucessfully() {
        DrinkDeposit deposit = drinkDepositMockModel();
        DrinkDepositDTO DTO = drinkDepositDtoMockEntry();
        when(drinkDepositRepository.save(any())).thenReturn(deposit);
        when(drinkDepositHistoryRepository.save(any())).thenReturn(drinkHistoryMockModel());
        when(drinkHistoryService.saveHistory(deposit)).thenReturn(drinkHistoryMockModel());


        DrinkDeposit saved = drinkDepositService.save(DTO);

        Assertions.assertEquals(deposit.getData(), saved.getData());
        Assertions.assertEquals(deposit.getResponsible(), saved.getResponsible());
        Assertions.assertEquals(deposit.getSection(), saved.getSection());
        Assertions.assertEquals(deposit.getMovimentType(), saved.getMovimentType());
        Assertions.assertEquals(deposit.getDrink(), saved.getDrink());
    }

    @Test
    void makeSaveWithNoSuccesfuly() {
        DrinkDeposit mockModel = drinkDepositMockModel();
        when(drinkDepositRepository.save(mockModel)).thenThrow(new IlegalRequest());

        Executable save = () -> drinkDepositService.save(drinkDepositDtoMockExit());

        Assertions.assertThrows(IlegalRequest.class, save);
    }

    @Test
    void makeATestInFindAllWithEmptyList() {
        when(drinkDepositRepository.findAll()).thenReturn(List.of());

        List<DrinkDeposit> histories = drinkDepositService.getAll();

        Assertions.assertTrue(histories.isEmpty());
    }

    @Test
    void makeATestInFindAllWithAItemInTheList() {
        when(drinkDepositRepository.findAll()).thenReturn(List.of(drinkDepositMockModel()));

        List<DrinkDeposit> histories = drinkDepositService.getAll();

        Assertions.assertFalse(histories.isEmpty());
    }

    @Test
    void makeATestInFindVolumesWithEmptyList() {
        when(drinkDepositRepository.findAll()).thenReturn(List.of());

        List<Map<String, Double>> histories = drinkDepositService.getAllVolumesPerDrink();

        Assertions.assertTrue(histories.isEmpty());
    }

    @Test
    void makeATestInFindVolumesWithAItemInTheList() {
        when(drinkDepositRepository.findAll()).thenReturn(List.of(drinkDepositMockModel()));

        List<Map<String, Double>> histories = drinkDepositService.getAllVolumesPerDrink();

        Assertions.assertFalse(histories.isEmpty());
    }


    @Test
    void makeTestOnTotalVolumeOnSection() {
        DrinkDeposit mockModel = drinkDepositMockModelSectionOut();
        when(drinkDepositRepository.save(mockModel)).thenThrow(new EntryError());

        Executable save = () -> drinkDepositRepository.save(mockModel);

        Assertions.assertThrows(EntryError.class, save);
    }

}
