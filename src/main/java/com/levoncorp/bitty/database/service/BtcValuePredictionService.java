package com.levoncorp.bitty.database.service;

import com.levoncorp.bitty.database.entity.BtcValuePrediction;
import com.levoncorp.bitty.database.repository.BtcValuePredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BtcValuePredictionService implements CurrencyValuePredictionService<BtcValuePrediction>{
    @Autowired
    private BtcValuePredictionRepository btcValuePredictionRepository;

    public BtcValuePrediction findById(long id) {
        Optional<BtcValuePrediction> btcValuePrediction = btcValuePredictionRepository.findById(id);
        return btcValuePrediction.orElse(null);
    }

    public List<BtcValuePrediction> findByTime(long time) {
        return btcValuePredictionRepository.findByTime(time);
    }

    @Override
    public void add(BtcValuePrediction value) {
        btcValuePredictionRepository.save(value);
    }

    @Override
    public void delete(long id) {
        btcValuePredictionRepository.deleteById(id);
    }

    @Override
    public List<BtcValuePrediction> listFromTime(long timeInMillis) {
        return btcValuePredictionRepository.findAll().stream().filter(o->o.getTime()>timeInMillis).collect(Collectors.toList());
    }

    @Override
    public BtcValuePrediction last() {
        return btcValuePredictionRepository.findTopByOrderByTimeDesc();
    }

    @Override
    public List<BtcValuePrediction> last(int number) {
        List<BtcValuePrediction> predictions = btcValuePredictionRepository.findAll();
        if (predictions.size() - number > 0) {
            predictions.subList(0, predictions.size() - number).clear();
        }
        return predictions;
    }
}
