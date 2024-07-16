package com.example.drinkdeposit.service;

import com.example.drinkdeposit.config.CopyPropertiesConfig;
import com.example.drinkdeposit.model.dto.DrinkConfigDTO;
import com.example.drinkdeposit.model.entities.DrinkConfig;
import com.example.drinkdeposit.repositories.DrinkConfigRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DrinkConfigService {

    private final DrinkConfigRepository drinkConfigRepository;

    public DrinkConfigService(DrinkConfigRepository drinkConfigRepository) {
        this.drinkConfigRepository = drinkConfigRepository;
    }

    public DrinkConfig patchingDrinkConfig(Integer id, DrinkConfigDTO drinkConfigDTO) {
        Optional<DrinkConfig> drinkConfigExisting = drinkConfigRepository.findById(id);
        return Stream.of(convertDrinkConfig(drinkConfigDTO))
                .map(drinkConfig -> {
                    CopyPropertiesConfig.copyProperties(drinkConfig.getMAX_ALCOHOLIC_CAPACITY(), drinkConfigExisting.get().getMAX_ALCOHOLIC_CAPACITY());
                    CopyPropertiesConfig.copyProperties(drinkConfig.getMAX_NONALCOHOLIC_CAPACITY(), drinkConfigExisting.get().getMAX_NONALCOHOLIC_CAPACITY());
                    CopyPropertiesConfig.copyProperties(drinkConfig.getPERMIT_SECTION(), drinkConfigExisting.get().getPERMIT_SECTION());
                    return drinkConfigRepository.save(drinkConfigExisting.get());
                })
                .findFirst()
                .orElseThrow();
    }




    public DrinkConfig convertDrinkConfig(DrinkConfigDTO drinkConfigDTO) {
        return new DrinkConfig(drinkConfigDTO.id(), drinkConfigDTO.MAX_ALCOHOLIC_CAPACITY(), drinkConfigDTO.MAX_NONALCOHOLIC_CAPACITY(), drinkConfigDTO.PERMIT_SECTION());
    }
}
