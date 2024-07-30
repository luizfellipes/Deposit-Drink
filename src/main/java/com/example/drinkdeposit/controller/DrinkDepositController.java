package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.service.DrinkDepositService;
import jakarta.validation.Valid;
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

    public DrinkDepositController(DrinkDepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/save")
    public ResponseEntity<DrinkDeposit> saveSell(@RequestBody @Valid DrinkDepositDTO drinkDepositDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(depositService.save(drinkDepositDTO));
    }

    @GetMapping
    public ResponseEntity<List<DrinkDeposit>>getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(depositService.getAll());
    }

    @GetMapping("/volume")
    public ResponseEntity<List<Map<String, Double>>> getAllVolume(){
        return ResponseEntity.status(HttpStatus.OK).body(depositService.getAllVolumesPerDrink());
    }

    @GetMapping("/availableSections")
    public ResponseEntity<List<Map<String, Double>>> getAvailableSections(){
        return ResponseEntity.status(HttpStatus.OK).body(depositService.availableSections());
    }

}
