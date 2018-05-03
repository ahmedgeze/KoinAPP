package com.example.ahmetserdargeze.koinplusapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmetserdargeze on 12.04.2018.
 */

public class SingleCoinResult {

    @SerializedName("change")
    @Expose
    private String change;
    @SerializedName("kur_id__kur_name")
    @Expose
    private String kurIdKurName;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("koin_id__koin_name")
    @Expose
    private String koinIdKoinName;
    @SerializedName("last")
    @Expose
    private String last;
    @SerializedName("volume")
    @Expose
    private String volume;

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getKurIdKurName() {
        return kurIdKurName;
    }

    public void setKurIdKurName(String kurIdKurName) {
        this.kurIdKurName = kurIdKurName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKoinIdKoinName() {
        return koinIdKoinName;
    }

    public void setKoinIdKoinName(String koinIdKoinName) {
        this.koinIdKoinName = koinIdKoinName;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
