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
import java.util.stream.Stream;

@Service
public class DrinkDepositService {

    private final DrinkDepositRepository repository;

    public DrinkDepositService(DrinkDepositRepository repository) {
        this.repository = repository;
    }

    public DrinkDeposit save(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .map(drinkDeposit -> {
                    verifySectionPermit(drinkDeposit);
                    sectionCapacity(drinkDeposit);
                    excessVolume(drinkDeposit);
                    entryAndExitOfstock(drinkDeposit);
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
                .map(drinkDeposit -> {
                    Map<String, Double> data = new HashMap<>();
                    data.put(drinkDeposit.getSection(), drinkDeposit.getDrink().getVolume());
                    return data;
                })
                .toList();
    }


    private boolean sectionExists(DrinkDeposit drinkDeposit) {
        return Stream.of(drinkDeposit)
                .anyMatch(drinkDeposit1 -> drinkDeposit1.getSection().equalsIgnoreCase(drinkDeposit.getSection()));
    }

    private void verifySectionPermit(DrinkDeposit drinkDeposit) {
        if (!drinkDeposit.getDrink().getDrinkConfig().getPERMIT_SECTION().contains(drinkDeposit.getSection().toUpperCase())) {
            throw new EntryError("Seção não permitida! Use apenas seções de A a E.");
        }
    }

    private boolean drinkTypeEquals(DrinkDeposit drinkDeposit) {
        return Stream.of(drinkDeposit)
                .filter(drinkDeposit1 -> drinkDeposit1.getSection().equalsIgnoreCase(drinkDeposit.getSection()))
                .findFirst()
                .map(drinkDeposit1 -> drinkDeposit1.getDrink().getDrinkType().equals(drinkDeposit.getDrink().getDrinkType()))
                .orElse(true);
    }


    private Double totalVolumeOnSection(DrinkDeposit drinkDeposit) {
        return Stream.of(drinkDeposit)
                .filter(drinkDeposit1 -> drinkDeposit1.getSection().equalsIgnoreCase(drinkDeposit.getSection()))
                .mapToDouble(drinkDeposit1 -> drinkDeposit1.getDrink().getVolume())
                .sum();
    }

    private void sectionCapacity(DrinkDeposit drinkDeposit) {
        if (sectionExists(drinkDeposit) && !drinkTypeEquals(drinkDeposit)) {
            throw new IlegalRequest("Não é permitido adicionar bebidas alcoólicas e não alcoólicas na mesma seção!");
        }
    }

    private void excessVolume(DrinkDeposit drinkDeposit) {
        if (totalVolumeOnSection(drinkDeposit) + drinkDeposit.getDrink().getVolume() > drinkDeposit.getDrink().maxCapacity()) {
            throw new EntryError("Não foi possível adicionar na seção pois o volume total foi excedente!");
        }
    }

    private void entryAndExitOfstock(DrinkDeposit drinkDeposit) {
        double totalVolume = totalVolumeOnSection(drinkDeposit);
        double actualVolume = drinkDeposit.getMovimentType().equals(MovimentType.ENTRY)
                ? totalVolume + drinkDeposit.getDrink().getVolume()
                : totalVolume - drinkDeposit.getDrink().getVolume();

        drinkDeposit.getDrink().totalVolumeInSection(actualVolume);
    }

    public DrinkDeposit convertDtoInModel(DrinkDepositDTO DTO) {
        return new DrinkDeposit(DTO.data(), DTO.responsible(), DTO.section(), DTO.movimentType(),
                new Drink(DTO.drink().drinkType(), DTO.drink().drinkName(), DTO.drink().volume()));
    }

}
