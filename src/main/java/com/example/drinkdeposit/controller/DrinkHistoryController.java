package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.model.entities.DrinkDepositHistory;
import com.example.drinkdeposit.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/drinkdeposit")
public class DrinkHistoryController {

    private final HistoryService historyService;

    public DrinkHistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<DrinkDepositHistory>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(historyService.getAll());
    }



}
