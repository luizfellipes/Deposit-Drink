package com.example.drinkdeposit.service;


import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.Drink;
import com.example.drinkdeposit.repositories.DrinkDepositRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class DrinkDepositService {

    private final DrinkDepositRepository repository;

    public DrinkDepositService(DrinkDepositRepository repository) {
        this.repository = repository;
    }

    public DrinkDeposit save(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .map(repository::save)
                .findFirst()
                .orElseThrow();
    }


    public List<DrinkDeposit> getAll() {
        return Optional.of(repository.findAll()).orElseThrow();
    }

    public List<Map<String, Integer>> getAllVolumesPerDrink() {
        return repository.findAll()
                .stream()
                .map(DrinkDeposit::updateSectionVolume)
                .toList();
    }


    public DrinkDeposit convertDtoInModel(DrinkDepositDTO DTO) {
        return new DrinkDeposit(DTO.data(), DTO.movimentType(), DTO.responsible(),
                new Drink(DTO.drink().drinkType(), DTO.drink().drinkName(), DTO.drink().volume(), DTO.drink().section()));
    }

}
