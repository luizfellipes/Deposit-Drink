package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.service.DrinkDepositService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/drinkdeposit")
public class DrinkDepositController {

    private final DrinkDepositService depositService;

    public DrinkDepositController(DrinkDepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping
    public ResponseEntity<DrinkDeposit> save(@RequestBody @Valid DrinkDepositDTO drinkDepositDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(depositService.save(drinkDepositDTO));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(depositService.getAll());
    }
}
