package com.levoncorp.bitty.database.service;

import com.levoncorp.bitty.database.entity.BtcValue;
import com.levoncorp.bitty.database.repository.BtcValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BtcValueService implements CurrencyValueService<BtcValue> {
    @Autowired
    private BtcValueRepository btcValueRepository;

    @Override
    public void add(BtcValue btcValue) {
        btcValueRepository.save(btcValue);
    }

    @Override
    public void delete(long id) {
        btcValueRepository.deleteById(id);
    }

    @Override
    public BtcValue findById(long id) {
        Optional<BtcValue> btcValueOptional = btcValueRepository.findById(id);
        return btcValueOptional.orElse(null);
    }

    @Override
    public List<BtcValue> listFromTime(long timeInMillis) {
        List<BtcValue> btcValues = btcValueRepository.findAll();
        return btcValues.
                        stream().
                        filter((btcValue -> btcValue.getTime() >= timeInMillis)).
                        collect(Collectors.toList());
    }

    @Override
    public BtcValue last() {
        return btcValueRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<BtcValue> last(int number) {
        List<BtcValue> allValues = btcValueRepository.findAll();
        if (allValues.size() > number)
            allValues.subList(0, allValues.size()-number).clear();
        return allValues;
    }
}
