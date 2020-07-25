package com.levoncorp.bitty.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class ForecastControllerTest {
    @Autowired
    private ForecastController forecastController;

    @Test
    void listCurrencyValuesFromTime() {
        forecastController.listCurrencyValuesFromTime(0L).forEach(System.out::println);
    }

    @Test
    void listPredictionsFromTime() throws InterruptedException {
        //for context be created:
        Thread.sleep(25000);
        forecastController.listPredictionsFromTime(0L).forEach(System.out::println);
    }

    @Test
    void prediction() {
    }

    @Test
    void currencyValue() {
    }
}