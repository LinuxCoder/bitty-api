package com.levoncorp.bitty.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "btc_value_pred")
public class BtcValuePrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "btc_value_pred_generator")
    @SequenceGenerator(name = "btc_value_pred_generator", sequenceName = "btc_value_pred_seq", allocationSize = 1)
    private long id;

    @Column
    private long time;

    @Column(name = "pred_value")
    private Double predictionValue;

    public BtcValuePrediction() {}

    public BtcValuePrediction(Double predictionValue, long time) {
        this.time = time;
        this.predictionValue = predictionValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Double getPredictionValue() {
        return predictionValue;
    }

    public void setPredictionValue(Double predictionValue) {
        this.predictionValue = predictionValue;
    }
}
