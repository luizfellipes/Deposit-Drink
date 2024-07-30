package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.model.entities.DrinkHistory;
import com.example.drinkdeposit.service.DrinkHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/drinkdeposit")
public class DrinkHistoryController {

    private final DrinkHistoryService drinkHistoryService;

    public DrinkHistoryController(DrinkHistoryService drinkHistoryService) {
        this.drinkHistoryService = drinkHistoryService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<DrinkHistory>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(drinkHistoryService.getAll());
    }


}
