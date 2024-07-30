package com.example.drinkdeposit.service;


import com.example.drinkdeposit.exceptions.EntryError;
import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
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
    private final DrinkConfigService drinkConfigService;


    public DrinkDepositService(DrinkDepositRepository repository, DrinkHistoryService drinkHistoryService, DrinkConfigService drinkConfigService) {
        this.repository = repository;
        this.drinkHistoryService = drinkHistoryService;
        this.drinkConfigService = drinkConfigService;
    }

    public DrinkDeposit save(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .map(drinkDeposit -> {
                    drinkHistoryService.saveHistory(drinkDeposit);
                    verifySectionPermit(drinkDeposit);
                    entryAndExitOfstock(drinkDeposit);
                    sectionDrinkIsEquals(drinkDeposit);
                    excessVolume(drinkDeposit);
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
                        drinkDeposit -> "Section: " + drinkDeposit.getSection() + ", DrinkType: " + drinkDeposit.getDrink().getDrinkType() + ", VolumeTotalInSection",
                        this::totalVolumeOnSection,
                        (existing, replacement) -> existing))
                .entrySet()
                .stream()
                .map(entry -> Map.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    public List<Map<String, Double>> availableSections() {
        Map<String, Double> sectionVolumeMap = repository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        drinkDeposit -> "Section: " + drinkDeposit.getSection(),
                        this::totalVolumeOnSection,
                        Double::max
                ));

        drinkConfigService.getDrinkConfig().getPERMIT_SECTION().forEach(section -> sectionVolumeMap.putIfAbsent("Section: " + section, 0.0));

        return sectionVolumeMap.entrySet()
                .stream()
                .map(entry -> Map.of(entry.getKey(), entry.getValue()))
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
        if (!drinkConfigService.getDrinkConfig().getPERMIT_SECTION().contains(drinkDeposit.getSection().toUpperCase())) {
            throw new EntryError("Seção não permitida! Use apenas seções de: " + drinkConfigService.getDrinkConfig().getPERMIT_SECTION());
        }
    }

    private boolean drinkTypeEquals(DrinkDeposit drinkDeposit) {
        return repository.findBySectionOrderByIdDesc(drinkDeposit.getSection())
                .stream()
                .map(drinkDeposit1 -> drinkDeposit1.getDrink().getDrinkType().equals(drinkDeposit.getDrink().getDrinkType()))
                .findFirst()
                .orElse(!drinkConfigService.getDrinkConfig().isDRINK_CAN_BE_TOGETHER());
    }

    private void sectionDrinkIsEquals(DrinkDeposit drinkDeposit) {
        if (sectionExists(drinkDeposit) && !drinkTypeEquals(drinkDeposit)) {
            if (!drinkConfigService.getDrinkConfig().isDRINK_CAN_BE_TOGETHER()) {
                throw new IlegalRequest("Não é permitido adicionar bebidas alcoólicas e não alcoólicas na mesma seção!");
            }
        }
    }

    private void excessVolume(DrinkDeposit drinkDeposit) {
        if (drinkDeposit.getMovimentType().equals(MovimentType.ENTRY) && totalVolumeOnSection(drinkDeposit) + drinkDeposit.getDrink().getVolume() > drinkConfigService.getDrinkConfig().maxCapacity(drinkDeposit.getDrink().getDrinkType())) {
            throw new EntryError("Não foi possível adicionar na seção pois o volume total foi excedente! " + "Volume maximo na seção: " + drinkConfigService.getDrinkConfig().maxCapacity(drinkDeposit.getDrink().getDrinkType()));
        }
    }

    private void entryAndExitOfstock(DrinkDeposit drinkDeposit) {
        double totalVolume = totalVolumeOnSection(drinkDeposit);
        double volume = drinkDeposit.getDrink().getVolume();
        double actualVolume = totalVolume + (drinkDeposit.getMovimentType().equals(MovimentType.ENTRY) ? volume : -volume);

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
