package com.example.ahmetserdargeze.koinplusapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmetserdargeze on 08.04.2018.
 */

public class Real_graphic_data {
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("koin")
    @Expose
    private String koin;
    @SerializedName("kur")
    @Expose
    private String kur;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKoin() {
        return koin;
    }

    public void setKoin(String koin) {
        this.koin = koin;
    }

    public String getKur() {
        return kur;
    }

    public void setKur(String kur) {
        this.kur = kur;
    }
}
