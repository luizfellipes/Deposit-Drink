package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkConfigDTO;
import com.example.drinkdeposit.model.entities.DrinkConfig;
import com.example.drinkdeposit.repositories.DrinkConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static com.example.drinkdeposit.config.CopyPropertiesConfig.copyProperties;


@Service
public class DrinkConfigService {

    private final DrinkConfigRepository drinkConfigRepository;
    private static final Logger log = LoggerFactory.getLogger(DrinkConfigService.class);

    public DrinkConfigService(DrinkConfigRepository drinkConfigRepository) {
        this.drinkConfigRepository = drinkConfigRepository;
    }

    public DrinkConfig patchingDrinkConfig(DrinkConfigDTO drinkConfigDTO) {
        DrinkConfig drinkConfigExists = getDrinkConfig();
        return Stream.of(convertDrinkConfig(drinkConfigDTO))
                .map(drinkConfig -> {
                    copyProperties(drinkConfig, drinkConfigExists);
                    log.info("realized patching of drink config");
                    return drinkConfigRepository.save(drinkConfigExists);
                })
                .findFirst()
                .orElseThrow();
    }

    public DrinkConfig getDrinkConfig() {
        log.info("getting drink config");
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
