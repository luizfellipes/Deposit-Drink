package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.exceptions.RequestsValidation;
import com.example.drinkdeposit.service.DrinkDepositService;
import com.example.drinkdeposit.service.DrinkHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class DrinkHistoryControllerTest {

    private MockMvc mockMvc;
    private DrinkHistoryService drinkHistoryService;

    @BeforeEach
    void setUp() {
        this.drinkHistoryService = mock(DrinkHistoryService.class);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new DrinkHistoryController(drinkHistoryService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setControllerAdvice(new RequestsValidation()).build();
        new ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());
    }

    @Test
    void makeTestOnGetAll() throws Exception {
        when(drinkHistoryService.getAll()).thenReturn(List.of());

        mockMvc.perform(get("/drinkdeposit/history"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void doTestErrorOnGetAll() throws Exception {
        doThrow(IlegalRequest.class).when(drinkHistoryService).getAll();

        mockMvc.perform(get("/drinkdeposit/history"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }


}
