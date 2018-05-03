package com.example.ahmetserdargeze.koinplusapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ahmetserdargeze on 12.04.2018.
 */

public class SingleCoinBody {

    @SerializedName("single_coin_result")
    @Expose
    private SingleCoinResult singleCoinResult;

    public SingleCoinResult getSingleCoinResult() {
        return singleCoinResult;
    }

    public void setSingleCoinResult(SingleCoinResult singleCoinResult) {
        this.singleCoinResult = singleCoinResult;
    }
}
