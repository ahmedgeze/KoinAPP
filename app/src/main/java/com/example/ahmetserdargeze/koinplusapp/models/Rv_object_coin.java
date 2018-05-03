package com.example.ahmetserdargeze.koinplusapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ahmetserdargeze on 10.03.2018.
 */

public class Rv_object_coin {
    @SerializedName("high")
    @Expose
    public String high;
    @SerializedName("last")
    @Expose
    public String last;
    @SerializedName("time")
    @Expose
    public String time;
    @SerializedName("base_volume")
    @Expose
    public String baseVolume;
    @SerializedName("kur_id__kur_name")
    @Expose
    public String kurIdKurName;
    @SerializedName("low")
    @Expose
    public String low;
    @SerializedName("koin_id__koin_name")
    @Expose
    public String koinIdKoinName;
    @SerializedName("change")
    @Expose
    public String change;
    @SerializedName("volume")
    @Expose
    public String volume;



    public Rv_object_coin(String high, String last, String time, String baseVolume, String kurIdKurName, String low, String koinIdKoinName, String change, String volume) {
        this.high = high;
        this.last = last;
        this.time = time;
        this.baseVolume = baseVolume;
        this.kurIdKurName = kurIdKurName;
        this.low = low;
        this.koinIdKoinName = koinIdKoinName;
        this.change = change;
        this.volume = volume;
    }



    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(String baseVolume) {
        this.baseVolume = baseVolume;
    }

    public String getKurIdKurName() {
        return kurIdKurName;
    }

    public void setKurIdKurName(String kurIdKurName) {
        this.kurIdKurName = kurIdKurName;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getKoinIdKoinName() {
        return koinIdKoinName;
    }

    public void setKoinIdKoinName(String koinIdKoinName) {
        this.koinIdKoinName = koinIdKoinName;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }



    @Override
    public String toString() {
        return "Rv_object_coin{" +
                "high='" + high + '\'' +
                ", last='" + last + '\'' +
                ", time='" + time + '\'' +
                ", baseVolume='" + baseVolume + '\'' +
                ", kurIdKurName='" + kurIdKurName + '\'' +
                ", low='" + low + '\'' +
                ", koinIdKoinName='" + koinIdKoinName + '\'' +
                ", change='" + change + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}