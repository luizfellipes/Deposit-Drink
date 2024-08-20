package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.service.DrinkDepositService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/drinkdeposit")
public class DrinkDepositController {

    private final DrinkDepositService depositService;
    private static final Logger log = LoggerFactory.getLogger(DrinkDepositController.class);

    public DrinkDepositController(DrinkDepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/save")
    public ResponseEntity<DrinkDeposit> save(@RequestBody @Valid DrinkDepositDTO drinkDepositDTO) {
        log.info("save solicited");
        return ResponseEntity.status(HttpStatus.CREATED).body(depositService.save(drinkDepositDTO));
    }

    @GetMapping
    public ResponseEntity<List<DrinkDeposit>>getAll(){
        log.info("get solicited");
        return ResponseEntity.status(HttpStatus.OK).body(depositService.getAll());
    }

    @GetMapping("/volume")
    public ResponseEntity<List<Map<String, Double>>> getAllVolume(){
        log.info("all volume solicited");
        return ResponseEntity.status(HttpStatus.OK).body(depositService.getAllVolumesPerDrink());
    }

    @GetMapping("/availableSections")
    public ResponseEntity<List<Map<String, Double>>> getAvailableSections(){
        log.info("available section solicited");
        return ResponseEntity.status(HttpStatus.OK).body(depositService.availableSections());
    }

}
