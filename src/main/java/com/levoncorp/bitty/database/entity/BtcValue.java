package com.levoncorp.bitty.database.entity;

import javax.persistence.*;

@Entity
@Table(name="btc_value")
public class BtcValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value")
    private double value;

    @Column(name = "time")
    private long time;

    public BtcValue() {}

    public BtcValue(double value, long time) {
        this.value = value;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "BtcValue{" +
                "id=" + id +
                ", value=" + value +
                ", time=" + time +
                '}';
    }
}
