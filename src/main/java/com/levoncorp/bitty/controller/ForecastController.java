package com.levoncorp.bitty.controller;

import com.levoncorp.bitty.storage.dto.BtcValuePredictionDto;
import com.levoncorp.bitty.forecast.service.CurrencyService;
import com.levoncorp.bitty.storage.dto.BtcValueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forecast")
@CrossOrigin(origins = "*")
public class ForecastController {
    @Autowired
    private CurrencyService<BtcValueDto> btcService;

    @GetMapping
    public String showForecast() {
        return "forecast";
    }

    @GetMapping("listValuesFromTime")
    @ResponseBody
    public List<BtcValueDto> listCurrencyValuesFromTime(
            @RequestParam(value = "fromTime", required = false, defaultValue = "0") Long time) {
        return btcService.listValuesFromTime(time);
    }

    @GetMapping("listPredictionsFromTime")
    @ResponseBody
    public List<BtcValuePredictionDto> listPredictionsFromTime(
            @RequestParam(value = "fromTime", required = false, defaultValue = "0") Long time) {
        return btcService.listPredictionsFromTime(time);
    }

    @GetMapping("/prediction")
    @ResponseBody
    public ResponseEntity<?> prediction() {
        return ResponseEntity.of(btcService.prediction());
    }

    @GetMapping("/currencyValue")
    @ResponseBody
    public ResponseEntity<?> currencyValue() {
        return ResponseEntity.of(btcService.currentValue());
    }
}
