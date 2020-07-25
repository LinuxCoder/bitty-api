package com.levoncorp.bitty.database.service;

import com.levoncorp.bitty.database.entity.BtcValuePrediction;
import com.levoncorp.bitty.storage.InterjacentBtcValuePredictionStorage;
import com.levoncorp.bitty.storage.dto.BtcValuePredictionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
@Profile("prod")
public class BtcValuePredictionUpdater {
    @Autowired
    private BtcValuePredictionService btcValuePredictionService;

    @Autowired
    private InterjacentBtcValuePredictionStorage interjacentStorage;

    private Set<Long> onPredictionIds = new HashSet<>();

    @Scheduled(fixedDelay = 1000 * 30)
    public void moveUnhandledBtcValuePredictionsToDatabase() {
        Iterator<BtcValuePredictionDto> bvpDtoIterator = interjacentStorage.getUnhandledBtcValuePredictions().iterator();
        while(bvpDtoIterator.hasNext()) {
            BtcValuePredictionDto bvpDto = bvpDtoIterator.next();
            if (bvpDto.getPredictionValue() == null) {
                BtcValuePrediction bvp = new BtcValuePrediction(bvpDto.getPredictionValue(), bvpDto.getPredictionTime());
                btcValuePredictionService.add(bvp);
                long id = bvp.getId();
                onPredictionIds.add(id);
                bvpDtoIterator.remove();
            }
        }
    }


    @Scheduled(fixedDelay = 1000 * 60)
    public void collectHandledBtcValuePredictions() {
        Iterator<Long> predictionIdIterator = onPredictionIds.iterator();
        while (predictionIdIterator.hasNext()) {
            Long id = predictionIdIterator.next();
            BtcValuePrediction bvp = btcValuePredictionService.findById(id);
            if (bvp.getPredictionValue() != null) {
                interjacentStorage.getHandledBtcValuePredictions().add(new BtcValuePredictionDto(bvp));
                predictionIdIterator.remove();
            }
        }
    }
}
