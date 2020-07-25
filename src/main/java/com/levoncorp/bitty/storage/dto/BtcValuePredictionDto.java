package com.levoncorp.bitty.storage.dto;

import com.levoncorp.bitty.database.entity.BtcValuePrediction;

public class BtcValuePredictionDto {
    private Double predictionValue;
    private long predictionTime;

    public BtcValuePredictionDto(long predictionTime) {
        this.predictionTime = predictionTime;
    }

    public BtcValuePredictionDto(Double predictionValue, long predictionTime) {
        this.predictionValue = predictionValue;
        this.predictionTime = predictionTime;
    }

    public BtcValuePredictionDto(BtcValuePrediction bvp) {
        this.predictionTime = bvp.getTime();
        this.predictionValue = bvp.getPredictionValue();
    }

    public Double getPredictionValue() {
        return predictionValue;
    }

    public void setPredictionValue(Double predictionValue) {
        this.predictionValue = predictionValue;
    }

    public long getPredictionTime() {
        return predictionTime;
    }

    public void setPredictionTime(long predictionTime) {
        this.predictionTime = predictionTime;
    }
}
