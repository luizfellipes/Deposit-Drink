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

import static com.example.drinkdeposit.MocksDTO.DrinkDepositDTOMock.*;
import static com.example.drinkdeposit.MocksModel.DrinkConfigMockModel.drinkConfigModel;
import static com.example.drinkdeposit.MocksModel.DrinkDepositMockModel.*;
import static com.example.drinkdeposit.MocksModel.DrinkHistoryMockModel.drinkHistoryMockModel;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class DrinkDepositServiceTest {

    private DrinkDepositService drinkDepositService;
    private DrinkDepositRepository drinkDepositRepository;
    private DrinkDepositHistoryRepository drinkDepositHistoryRepository;

    @BeforeEach
    void setUp() {
        this.drinkDepositRepository = mock(DrinkDepositRepository.class);
        this.drinkDepositHistoryRepository = mock(DrinkDepositHistoryRepository.class);
        DrinkHistoryService drinkHistoryService = new DrinkHistoryService(drinkDepositHistoryRepository);
        this.drinkDepositService = new DrinkDepositService(drinkDepositRepository, drinkHistoryService, drinkConfigModel());
    }

    @Test
    void makeASaveWithSucessfully() {
        DrinkDeposit deposit = drinkDepositMockModel();
        DrinkDepositDTO DTO = drinkDepositDtoMockEntryAlcoholic();
        when(drinkDepositRepository.save(any())).thenReturn(deposit);
        when(drinkDepositHistoryRepository.save(any())).thenReturn(drinkHistoryMockModel());


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
    void makeTestExitWithoutVolume() {
        DrinkDeposit mockModel = drinkDepositMockModelSectionOutNonAcloholic();
        when(drinkDepositRepository.save(mockModel)).thenThrow(new IlegalRequest());

        Executable save = () -> drinkDepositService.save(drinkDepositDtoMockExit());

        Assertions.assertThrows(IlegalRequest.class, save);
    }

    @Test
    void makeTestOnTotalVolumeOnSection() {
        DrinkDepositDTO DTO = drinkDepositDtoMockSectionOut();
        when(drinkDepositRepository.save(any())).thenThrow(EntryError.class);
        when(drinkDepositHistoryRepository.save(any())).thenReturn(drinkHistoryMockModel());

        Executable save = () -> drinkDepositService.save(DTO);

        Assertions.assertThrows(EntryError.class, save);
    }

    @Test
    void makeTestOnSectionExists() {
        DrinkDepositDTO DTO = drinkDepositDtoMockSectionOut();
        when(drinkDepositRepository.save(any())).thenThrow(new EntryError());

        Executable save = () -> drinkDepositService.save(DTO);

        Assertions.assertThrows(EntryError.class, save);
    }

    @Test
    void makeTestOnSectionDrinkIsEquals() {
        DrinkDepositDTO DTO = drinkDepositDtoMockEntryAlcoholic();
        DrinkDepositDTO DTO1 = drinkDepositDtoMockEntryNonAlcoholic();
        when(drinkDepositRepository.save(any())).thenThrow(new IlegalRequest());
        when(drinkDepositHistoryRepository.save(any())).thenReturn(drinkHistoryMockModel());

        Executable save = () -> drinkDepositService.save(DTO);
        Executable save1 = () -> drinkDepositService.save(DTO1);


        Assertions.assertNotEquals(save1, save);
        Assertions.assertThrows(IlegalRequest.class, save1);
    }

    @Test
    void makeTestOnExcessVolume() {
        DrinkDeposit mockModel = drinkDepositMockModelSectionOutNonAcloholic();
        when(drinkDepositRepository.save(mockModel)).thenThrow(new EntryError());

        Executable save = () -> drinkDepositService.save(drinkDepositDtoMockEntryExcessVolume());

        Assertions.assertThrows(EntryError.class, save);
    }

    @Test
    void makeTestOnEntryAndExitOfstock() {
        DrinkDeposit mockModel1 = drinkDepositMockModelSectionOutNonAcloholic();
        when(drinkDepositRepository.save(mockModel1)).thenThrow(new IlegalRequest());

        Executable save1 = () -> drinkDepositService.save(drinkDepositDtoMockExit());

        Assertions.assertThrows(IlegalRequest.class, save1);
    }


}
