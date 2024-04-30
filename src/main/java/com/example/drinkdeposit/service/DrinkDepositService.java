package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.repositories.DrinkDepositRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DrinkDepositService {

    private final DrinkDepositRepository repository;

    public DrinkDepositService(DrinkDepositRepository repository) {
        this.repository = repository;
    }


    public DrinkDeposit save(DrinkDepositDTO drinkDepositDTO) {
        return repository.save(convertDtoInModel(drinkDepositDTO));
    }

    public List<DrinkDeposit> getAll() {
        return Optional.of(repository.findAll()).orElseThrow();
    }

    public DrinkDeposit convertDtoInModel(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(drinkDepositDTO).map(DTO -> new DrinkDeposit(DTO.drinkType(), DTO.drinkName(), DTO.volume(), DTO.data())).findFirst().orElse(null);
    }


}
