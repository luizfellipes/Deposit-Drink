package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.Drink;
import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.repositories.DrinkDepositRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DrinkDepositService {

    private final DrinkDepositRepository repository;

    public DrinkDepositService(DrinkDepositRepository repository) {
        this.repository = repository;
    }


    public DrinkDeposit save(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .map(repository::save)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("não foi possivel realizar a entrada !"));
    }

    public List<DrinkDeposit> getAll() {
        return Optional.of(repository.findAll()).orElseThrow();
    }

    public List<Map<String, Object>> getAllVolumesPerDrink() {
        return Optional.of(repository.findAll().stream()
                        .map(drinkDeposit -> {
                            Map<String, Object> data = new HashMap<>();
                            List<String> sections = drinkDeposit.getSection();
                            int volume = drinkDeposit.getDrink().getVolume();

                            int maxCapacity = drinkDeposit.getDrink().getDrinkType().equals(DrinkType.ALCOHOLIC) ?
                                    drinkDeposit.getDrink().getDrinkConfig().getMAX_ALCOHOLIC_CAPACITY() :
                                    drinkDeposit.getDrink().getDrinkConfig().getMAX_NONALCOHOLIC_CAPACITY();

                            for (String section : sections) {
                                drinkDeposit.updateSectionVolume(section, volume, maxCapacity);
                            }

                            data.put("sectionVolumes", drinkDeposit.getSectionVolumes());
                            return data;
                        }).toList())
                .orElseThrow();
    }

    public DrinkDeposit convertDtoInModel(DrinkDepositDTO DTO) {
        return new DrinkDeposit(DTO.data(), DTO.movimentType(), DTO.responsible(), DTO.section(),
                new Drink(DTO.drink().drinkType(), DTO.drink().drinkName(), DTO.drink().volume()));
    }

}
