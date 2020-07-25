package com.levoncorp.bitty.storage.dto;

import com.levoncorp.bitty.database.entity.BtcValue;

public class BtcValueDto {
    private double value;
    private long time;

    public BtcValueDto(double value, long time) {
        this.value = value;
        this.time = time;
    }

    public BtcValueDto(BtcValue btcValue) {
        this.value = btcValue.getValue();
        this.time = btcValue.getTime();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
