package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkConfigDTO;
import com.example.drinkdeposit.model.entities.DrinkConfig;
import com.example.drinkdeposit.repositories.DrinkConfigRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


@Service
public class DrinkConfigService {

    private final DrinkConfigRepository drinkConfigRepository;

    public DrinkConfigService(DrinkConfigRepository drinkConfigRepository) {
        this.drinkConfigRepository = drinkConfigRepository;
    }

    public DrinkConfig patchingDrinkConfig(DrinkConfigDTO drinkConfigDTO) {
        return Stream.of(convertDrinkConfig(drinkConfigDTO))
                .map(drinkConfig -> {
                    drinkConfig.updateConfig(drinkConfig.getId(), drinkConfig.getMAX_ALCOHOLIC_CAPACITY(), drinkConfig.getMAX_NONALCOHOLIC_CAPACITY(), drinkConfig.getPERMIT_SECTION());
                    return drinkConfigRepository.save(drinkConfig);
                })
                .findFirst()
                .orElseThrow();
    }

    private DrinkConfig convertDrinkConfig(DrinkConfigDTO drinkConfigDTO) {
        return Stream.of(drinkConfigDTO)
                .map(DTO -> new DrinkConfig(drinkConfigDTO.id(), drinkConfigDTO.MAX_ALCOHOLIC_CAPACITY(), drinkConfigDTO.MAX_NONALCOHOLIC_CAPACITY(), drinkConfigDTO.PERMIT_SECTION()))
                .findFirst()
                .orElseThrow();
    }
}
