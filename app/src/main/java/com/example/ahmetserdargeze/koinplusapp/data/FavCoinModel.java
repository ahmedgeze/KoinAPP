package com.example.ahmetserdargeze.koinplusapp.data;

/**
 * Created by ahmetserdargeze on 11.04.2018.
 */

public class FavCoinModel {
    private int koin_id;
    private String koin_name;
    private String kur;
    private static int fav_state=0;
    private static int kur_state=0;
    private static String koin__name;

    public static String getKoin__name() {
        return koin__name;
    }

    public static void setKoin__name(String koin__name) {
        FavCoinModel.koin__name = koin__name;
    }

    public FavCoinModel(int koin_id, String koin_name, String kur) {
        this.koin_id = koin_id;
        this.koin_name = koin_name;
        this.kur = kur;
    }

    public FavCoinModel() {


    }

    public static void favState_Change(int a){
        fav_state=a;
    }

    public static void kurState_change(int b){
        kur_state=b;
    }

    public static int getFav_state() {
        return fav_state;
    }

    public static int getKur_state() {
        return kur_state;
    }

    public int getKoinId() {
        return koin_id;
    }

    public void setKoinId(int koin_id) {
        this.koin_id = koin_id;
    }

    public String getKoin_name() {
        return koin_name;
    }

    public void setKoin_name(String koin_name) {
        this.koin_name = koin_name;
    }

    public String getKur() {
        return kur;
    }

    public void setKur(String kur) {
        this.kur = kur;
    }
}
