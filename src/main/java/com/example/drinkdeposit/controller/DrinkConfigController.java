package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.model.dto.DrinkConfigDTO;
import com.example.drinkdeposit.model.entities.DrinkConfig;
import com.example.drinkdeposit.service.DrinkConfigService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin (origins = "http://localhost:8080")
@RequestMapping("/drinkdeposit")
public class DrinkConfigController {

    private final DrinkConfigService drinkConfigService;
    private static final Logger log = LoggerFactory.getLogger(DrinkConfigController.class);

    public DrinkConfigController(DrinkConfigService drinkConfigService) {
        this.drinkConfigService = drinkConfigService;
    }

    @PatchMapping("/sections")
    public ResponseEntity<DrinkConfig> patchingConfig(@RequestBody @Valid DrinkConfigDTO drinkConfigDTO) {
        log.info("patching config solicited");
        return ResponseEntity.status(HttpStatus.OK).body(drinkConfigService.patchingDrinkConfig(drinkConfigDTO));
    }
}
