package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.model.entities.DrinkHistory;
import com.example.drinkdeposit.service.DrinkHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(DrinkHistoryController.class);

    public DrinkHistoryController(DrinkHistoryService drinkHistoryService) {
        this.drinkHistoryService = drinkHistoryService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<DrinkHistory>> getAll() {
        log.info("history solicited");
        return ResponseEntity.status(HttpStatus.OK).body(drinkHistoryService.getAll());
    }


}
