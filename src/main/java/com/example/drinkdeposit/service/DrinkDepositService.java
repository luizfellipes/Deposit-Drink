package com.example.drinkdeposit.service;


import com.example.drinkdeposit.exceptions.EntryError;
import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkConfig;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.Drink;
import com.example.drinkdeposit.model.enums.MovimentType;
import com.example.drinkdeposit.repositories.DrinkDepositRepository;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DrinkDepositService {

    private final DrinkDepositRepository repository;
    private final DrinkHistoryService drinkHistoryService;
    private final DrinkConfig drinkConfig;


    public DrinkDepositService(DrinkDepositRepository repository, DrinkHistoryService drinkHistoryService, DrinkConfig drinkConfig) {
        this.repository = repository;
        this.drinkHistoryService = drinkHistoryService;
        this.drinkConfig = drinkConfig;
    }

    public DrinkDeposit save(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .map(drinkDeposit -> {
                    verifySectionPermit(drinkDeposit);
                    entryAndExitOfstock(drinkDeposit);
                    sectionCapacity(drinkDeposit);
                    excessVolume(drinkDeposit);
                    drinkHistoryService.saveHistory(drinkDeposit);
                    return repository.save(drinkDeposit);
                })
                .findFirst()
                .orElseThrow();
    }

    public List<DrinkDeposit> getAll() {
        return Optional.of(repository.findAll()).orElseThrow();
    }

    public List<Map<String, Double>> getAllVolumesPerDrink() {
        return repository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        DrinkDeposit::getSection,
                        this::totalVolumeOnSection,
                        (existing, replacement) -> existing))
                .entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Double> sectionAndVolume = new HashMap<>();
                    sectionAndVolume.put(entry.getKey(), entry.getValue());
                    return sectionAndVolume;
                })
                .toList();
    }

    private Double totalVolumeOnSection(DrinkDeposit drinkDeposit) {
        return repository.findBySectionOrderByIdDesc(drinkDeposit.getSection())
                .stream()
                .findFirst()
                .map(drinkDeposit1 -> drinkDeposit1.getDrink().getTotalVolumeInSection())
                .orElse(0.0);
    }

    private boolean sectionExists(DrinkDeposit drinkDeposit) {
        return Stream.of(drinkDeposit)
                .anyMatch(drinkDeposit1 -> drinkDeposit1.getSection().equalsIgnoreCase(drinkDeposit.getSection()));
    }

    private void verifySectionPermit(DrinkDeposit drinkDeposit) {
        if (!drinkConfig.getPERMIT_SECTION().contains(drinkDeposit.getSection().toUpperCase())) {
            throw new EntryError("Seção não permitida! Use apenas seções de: " + drinkConfig.getPERMIT_SECTION());
        }
    }

    private boolean drinkTypeEquals(DrinkDeposit drinkDeposit) {
        return Stream.of(drinkDeposit)
                .filter(drinkDeposit1 -> drinkDeposit1.getSection().equalsIgnoreCase(drinkDeposit.getSection()))
                .findFirst()
                .map(drinkDeposit1 -> drinkDeposit1.getDrink().getDrinkType().equals(drinkDeposit.getDrink().getDrinkType()))
                .orElse(true);
    }

    private void sectionCapacity(DrinkDeposit drinkDeposit) {
        if (sectionExists(drinkDeposit) && !drinkTypeEquals(drinkDeposit)) {
            throw new IlegalRequest("Não é permitido adicionar bebidas alcoólicas e não alcoólicas na mesma seção!");
        }
    }

    private void excessVolume(DrinkDeposit drinkDeposit) {
        if (drinkDeposit.getMovimentType().equals(MovimentType.ENTRY) && totalVolumeOnSection(drinkDeposit) + drinkDeposit.getDrink().getVolume() > drinkConfig.maxCapacity(drinkDeposit.getDrink().getDrinkType())) {
            throw new EntryError("Não foi possível adicionar na seção pois o volume total foi excedente! " + "Volume maximo na seção: " + drinkConfig.maxCapacity(drinkDeposit.getDrink().getDrinkType()));
        }
    }

    private void entryAndExitOfstock(DrinkDeposit drinkDeposit) {
        double totalVolume = totalVolumeOnSection(drinkDeposit);
        double volume = drinkDeposit.getDrink().getVolume();
        double actualVolume = drinkDeposit.getMovimentType().equals(MovimentType.ENTRY)
                ? totalVolume + volume
                : totalVolume - volume;

        if (totalVolume <= 0 && drinkDeposit.getMovimentType().equals(MovimentType.EXIT)) {
            throw new IlegalRequest("não é possivel realizar uma saida sem possuir volume no estoque !");
        }
        drinkDeposit.getDrink().totalVolumeInSection(actualVolume);
    }

    private DrinkDeposit convertDtoInModel(DrinkDepositDTO DTO) {
        return new DrinkDeposit(DTO.data(), DTO.responsible(), DTO.section(), DTO.movimentType(),
                new Drink(DTO.drink().drinkType(), DTO.drink().drinkName(), DTO.drink().volume()));
    }

}
