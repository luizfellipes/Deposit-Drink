package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkConfigDTO;
import com.example.drinkdeposit.model.entities.DrinkConfig;
import com.example.drinkdeposit.repositories.DrinkConfigRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static com.example.drinkdeposit.config.CopyPropertiesConfig.copyProperties;


@Service
public class DrinkConfigService {

    private final DrinkConfigRepository drinkConfigRepository;

    public DrinkConfigService(DrinkConfigRepository drinkConfigRepository) {
        this.drinkConfigRepository = drinkConfigRepository;
    }

    public DrinkConfig patchingDrinkConfig(DrinkConfigDTO drinkConfigDTO) {
        DrinkConfig drinkConfigExistis = getDrinkConfig();
        return Stream.of(convertDrinkConfig(drinkConfigDTO))
                .map(drinkConfig -> {
                    copyProperties(drinkConfig, drinkConfigExistis);
                    return drinkConfigRepository.save(drinkConfigExistis);
                })
                .findFirst()
                .orElseThrow();
    }

    public DrinkConfig getDrinkConfig() {
        return drinkConfigRepository.findById(1).orElseGet(DrinkConfig::new);
    }

    private DrinkConfig convertDrinkConfig(DrinkConfigDTO drinkConfigDTO) {
        return new DrinkConfig(
                drinkConfigDTO.id(),
                drinkConfigDTO.MAX_ALCOHOLIC_CAPACITY(),
                drinkConfigDTO.MAX_NONALCOHOLIC_CAPACITY(),
                drinkConfigDTO.PERMIT_SECTION(),
                drinkConfigDTO.DRINK_CAN_BE_TOGETHER());
    }
}
