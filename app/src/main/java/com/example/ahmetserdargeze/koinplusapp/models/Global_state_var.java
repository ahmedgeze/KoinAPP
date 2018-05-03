package com.example.ahmetserdargeze.koinplusapp.models;

/**
 * Created by ahmetserdargeze on 18.04.2018.
 */

public  class Global_state_var {
    public static int coin=0;
    public static int fav_state=0;

    public static int bottombar_state=0;

    public static String graphicKurName ="USDT";

    public static String graphicKoinName="BTC";

    public static int threadCount=0;

    public static int getThreadCount() {
        return threadCount;
    }

    public static void setThreadCount() {
        threadCount++;
    }

    public static String getGraphicKoinName() {
        return graphicKoinName;
    }

    public static void setGraphicKoinName(String graphicKoinName) {
        Global_state_var.graphicKoinName = graphicKoinName;
    }

    public static String getGraphicKurName() {
        return graphicKurName;
    }

    public static void setGraphicCoinName() {
        if(coin==0)
            graphicKurName ="USDT";
        else if(coin==1)
            graphicKurName ="BTC";
        else if(coin==2)
            graphicKurName ="USDT";
    }

    public static int getCoin() {
        return coin;
    }



    public static void setCoin(int coin) {
        Global_state_var.coin = coin;
    }

    public static int getFav_state() {
        return fav_state;
    }

    public static void setFav_state(int fav_state) {
        Global_state_var.fav_state = fav_state;
    }

    public static int getBottombar_state() {
        return bottombar_state;
    }

    public static void setBottombar_state(int bottombar_state) {
        Global_state_var.bottombar_state = bottombar_state;
    }
}
