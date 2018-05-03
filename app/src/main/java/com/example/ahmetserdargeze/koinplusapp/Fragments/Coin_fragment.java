package com.example.ahmetserdargeze.koinplusapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ahmetserdargeze.koinplusapp.R;
import com.example.ahmetserdargeze.koinplusapp.data.Database;
import com.example.ahmetserdargeze.koinplusapp.data.FavCoinModel;
import com.example.ahmetserdargeze.koinplusapp.models.Result;
import com.example.ahmetserdargeze.koinplusapp.models.Rv_object_coin;

import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinBody;
import com.example.ahmetserdargeze.koinplusapp.models.SingleCoinResult;
import com.example.ahmetserdargeze.koinplusapp.recylerview.BtcAdapter;
import com.example.ahmetserdargeze.koinplusapp.recylerview.ResultAdapter;
import com.example.ahmetserdargeze.koinplusapp.retrofit.APIService;
import com.example.ahmetserdargeze.koinplusapp.retrofit.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmetserdargeze on 10.03.2018.
 */

public class Coin_fragment extends Fragment {
    LinearLayout onClickBTC;
    LinearLayout onClickUSDT;
    LinearLayout onClickETH;

    LinearLayout topStar;

    int FavCounter=1;
    int kur;


    public APIService usdToCoin;
    public APIService btcToCoin;
    public APIService ethToCoin;


    View view;

    RecyclerView usdtcoinrecyler;
    RecyclerView.LayoutManager mLayoutManager;

    ResultAdapter usdtAdapter;
    ResultAdapter btcAdapter;
    ResultAdapter ethAdapter;

    BtcAdapter favAdapter;


    Result usdt_response =new Result();
    Result btc_response =new Result();
    Result eth_response =new Result();



    ArrayList<Rv_object_coin> result_USDresponse =new ArrayList<Rv_object_coin>();
    ArrayList<Rv_object_coin> result_ETHresponse =new ArrayList<Rv_object_coin>();
    ArrayList<Rv_object_coin> result_BTCresponse =new ArrayList<Rv_object_coin>();

    SingleCoinBody favBody_usdt =new SingleCoinBody();
    SingleCoinResult favResult_usdt =new SingleCoinResult();
    ArrayList<SingleCoinResult> favList_usdt =new ArrayList<SingleCoinResult>();

    SingleCoinBody favBody_eth =new SingleCoinBody();
    ArrayList<SingleCoinResult> favList_eth =new ArrayList<SingleCoinResult>();
    SingleCoinResult favResult_eth =new SingleCoinResult();

    SingleCoinBody favBody_btc=new SingleCoinBody();
    ArrayList<SingleCoinResult> favList_btc =new ArrayList<SingleCoinResult>();
    SingleCoinResult favResult_btc =new SingleCoinResult();








    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FavCoinModel.kurState_change(3);
        FavCoinModel.favState_Change(0);

//        onClickETH =(LinearLayout) view.findViewById(R.id.topEthLay);
//        onClickUSDT =(LinearLayout) view.findViewById(R.id.topUsdtLay);
//        onClickBTC=(LinearLayout) view.findViewById(R.id.topBtcLay);








    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.usdt_coin_fragment,container,false);
        usdtcoinrecyler=(RecyclerView) view.findViewById(R.id.ucf_usdtcoinrecyler);

        mLayoutManager=new LinearLayoutManager(getActivity());

        FavCoinModel.favState_Change(0);
        FavCoinModel.kurState_change(3);


        usdToCoin=ApiUtils.getAPIService();
        usdToCoin.getCoinWithType("USDT").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                usdt_response =response.body();
                result_USDresponse = usdt_response.getResult();
                String y=result_USDresponse.size()+"";
                Log.i("dolar boyutu",y);
                usdtAdapter = new ResultAdapter(result_USDresponse);
                usdtcoinrecyler.setLayoutManager(mLayoutManager);
                usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                usdtcoinrecyler.setAdapter(usdtAdapter);


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });








        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        onClickETH = (LinearLayout) view.findViewById(R.id.topEthLay);
        onClickUSDT = (LinearLayout) view.findViewById(R.id.topUsdtLay);
        onClickBTC = (LinearLayout) view.findViewById(R.id.topBtcLay);
        onClickUSDT.setBackgroundResource(R.color.topBarSelected);

        topStar = (LinearLayout) view.findViewById(R.id.topFavStar);
        topStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavCoinModel.kurState_change(3);

                onClickBTC.setBackgroundResource(R.color.toolbar_bg);
                onClickETH.setBackgroundResource(R.color.toolbar_bg);
                onClickUSDT.setBackgroundResource(R.color.topBarSelected);

                if (FavCoinModel.getFav_state() == 0) {
                    FavCoinModel.favState_Change(1);
                } else if (FavCoinModel.getFav_state() == 1) {
                    FavCoinModel.favState_Change(0);
                }


                if (FavCoinModel.getFav_state() == 1) {


                    topStar.setBackgroundResource(R.drawable.star_fill);


//                    FavCoinModel.favState_Change(1);


                    List<FavCoinModel> favcoin_list_a = new ArrayList<FavCoinModel>();
                    kur = FavCoinModel.getKur_state();


                    Database db = new Database(view.getContext());
                    db.openCon();

                    favcoin_list_a = db.ListByKurId(kur + "");
                    for (int a = 0; a < favcoin_list_a.size(); a++) {
                        usdToCoin = ApiUtils.getAPIService();


                        usdToCoin.getSingleCoin("USDT", favcoin_list_a.get(a).getKoin_name()).enqueue(new Callback<SingleCoinBody>() {
                            @Override
                            public void onResponse(Call<SingleCoinBody> call, Response<SingleCoinBody> response) {
                                result_USDresponse.clear();
                                result_BTCresponse.clear();
                                result_ETHresponse.clear();
                                usdtcoinrecyler.getAdapter().notifyDataSetChanged();

                                favBody_usdt = response.body();
                                favResult_usdt = favBody_usdt.getSingleCoinResult();
                                favList_usdt.add(favResult_usdt);


                                favAdapter = new BtcAdapter(favList_usdt);
                                usdtcoinrecyler.setLayoutManager(mLayoutManager);
                                usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                                usdtcoinrecyler.setAdapter(favAdapter);
                                usdtcoinrecyler.invalidate();

                            }

                            @Override
                            public void onFailure(Call<SingleCoinBody> call, Throwable t) {

                            }
                        });

                    }


                } else if (FavCoinModel.getFav_state() == 0) {
                    topStar.setBackgroundResource(R.drawable.star_fill_gl);
                    favList_usdt.clear();
                    usdtcoinrecyler.getAdapter().notifyDataSetChanged();

                    FavCoinModel.favState_Change(0);
                    int kur = FavCoinModel.getKur_state();
                    String kur_name = "USDT";


                    usdToCoin = ApiUtils.getAPIService();
                    usdToCoin.getCoinWithType(kur_name).enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            usdt_response = response.body();
                            result_USDresponse = usdt_response.getResult();

                            usdtAdapter = new ResultAdapter(result_USDresponse);

                            usdtcoinrecyler.setLayoutManager(mLayoutManager);
                            usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                            usdtcoinrecyler.setAdapter(usdtAdapter);
                            usdtcoinrecyler.invalidate();


                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                        }
                    });


                }

            }
        });


        onClickBTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBTC.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.topBarSelected));
                onClickUSDT.setBackgroundResource(R.color.toolbar_bg);
                onClickETH.setBackgroundResource(R.color.toolbar_bg);

                FavCoinModel.kurState_change(1);

                //1 btc active,2 eth active ,3 usdt active
                if (FavCoinModel.getFav_state() == 1) {


                    topStar.setBackgroundResource(R.drawable.star_fill);


                    List<FavCoinModel> favcoin_list_a = new ArrayList<FavCoinModel>();
                    kur = FavCoinModel.getKur_state();


                    Database db = new Database(view.getContext());
                    db.openCon();

                    favcoin_list_a = db.ListByKurId(kur + "");
                    for (int a = 0; a < favcoin_list_a.size(); a++) {
                        btcToCoin = ApiUtils.getAPIService();


                        btcToCoin.getSingleCoin("BTC", favcoin_list_a.get(a).getKoin_name()).enqueue(new Callback<SingleCoinBody>() {
                            @Override
                            public void onResponse(Call<SingleCoinBody> call, Response<SingleCoinBody> response) {
                                result_USDresponse.clear();
                                result_BTCresponse.clear();
                                result_ETHresponse.clear();
                                usdtcoinrecyler.getAdapter().notifyDataSetChanged();

                                favBody_btc = response.body();
                                favResult_btc = favBody_btc.getSingleCoinResult();
                                favList_btc.add(favResult_btc);


                                favAdapter = new BtcAdapter(favList_btc);
                                usdtcoinrecyler.setLayoutManager(mLayoutManager);
                                usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                                usdtcoinrecyler.setAdapter(favAdapter);
                                usdtcoinrecyler.invalidate();

                            }

                            @Override
                            public void onFailure(Call<SingleCoinBody> call, Throwable t) {

                            }
                        });

                    }


                } else if (FavCoinModel.getFav_state() == 0) {
                    topStar.setBackgroundResource(R.drawable.star_fill_gl);
                    favList_btc.clear();
                    usdtcoinrecyler.getAdapter().notifyDataSetChanged();

                    int kur = FavCoinModel.getKur_state();
                    String kur_name = "BTC";

                    btcToCoin = ApiUtils.getAPIService();
                    btcToCoin.getCoinWithType(kur_name).enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            btc_response = response.body();
                            result_BTCresponse = btc_response.getResult();

                            btcAdapter = new ResultAdapter(result_BTCresponse);

                            usdtcoinrecyler.setLayoutManager(mLayoutManager);
                            usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                            usdtcoinrecyler.setAdapter(btcAdapter);
                            usdtcoinrecyler.invalidate();


                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                        }
                    });


                }


            }
        });


        onClickETH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBTC.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.toolbar_bg));
                onClickUSDT.setBackgroundResource(R.color.toolbar_bg);
                onClickETH.setBackgroundResource(R.color.topBarSelected);

                FavCoinModel.kurState_change(2);

                //1 btc active,2 eth active ,3 usdt active
                if (FavCoinModel.getFav_state() == 1) {


                    topStar.setBackgroundResource(R.drawable.star_fill);


                    List<FavCoinModel> favcoin_list_a = new ArrayList<FavCoinModel>();
                    kur = FavCoinModel.getKur_state();


                    Database db = new Database(view.getContext());
                    db.openCon();

                    favcoin_list_a = db.ListByKurId(kur + "");
                    for (int a = 0; a < favcoin_list_a.size(); a++) {
                        ethToCoin = ApiUtils.getAPIService();


                        ethToCoin.getSingleCoin("ETH", favcoin_list_a.get(a).getKoin_name()).enqueue(new Callback<SingleCoinBody>() {
                            @Override
                            public void onResponse(Call<SingleCoinBody> call, Response<SingleCoinBody> response) {
                                result_USDresponse.clear();
                                result_BTCresponse.clear();
                                result_ETHresponse.clear();
                                usdtcoinrecyler.getAdapter().notifyDataSetChanged();

                                favBody_eth = response.body();
                                favResult_eth = favBody_eth.getSingleCoinResult();
                                favList_eth.add(favResult_eth);


                                favAdapter = new BtcAdapter(favList_eth);
                                usdtcoinrecyler.setLayoutManager(mLayoutManager);
                                usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                                usdtcoinrecyler.setAdapter(favAdapter);
                                usdtcoinrecyler.invalidate();

                            }

                            @Override
                            public void onFailure(Call<SingleCoinBody> call, Throwable t) {

                            }
                        });

                    }


                } else if (FavCoinModel.getFav_state() == 0) {
                    topStar.setBackgroundResource(R.drawable.star_fill_gl);
                    favList_eth.clear();
                    usdtcoinrecyler.getAdapter().notifyDataSetChanged();

                    int kur = FavCoinModel.getKur_state();
                    String kur_name = "ETH";

                    ethToCoin = ApiUtils.getAPIService();
                    ethToCoin.getCoinWithType(kur_name).enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            eth_response = response.body();
                            result_ETHresponse = eth_response.getResult();

                            ethAdapter = new ResultAdapter(result_ETHresponse);

                            usdtcoinrecyler.setLayoutManager(mLayoutManager);
                            usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                            usdtcoinrecyler.setAdapter(ethAdapter);
                            usdtcoinrecyler.invalidate();


                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                        }
                    });


                }


            }
        });


        onClickUSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickBTC.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.toolbar_bg));
                onClickUSDT.setBackgroundResource(R.color.topBarSelected);
                onClickETH.setBackgroundResource(R.color.toolbar_bg);

                FavCoinModel.kurState_change(3);

                //1 btc active,2 eth active ,3 usdt active
                if(FavCoinModel.getFav_state()==1){


                    topStar.setBackgroundResource(R.drawable.star_fill);
                    List<FavCoinModel> favcoin_list_a=new ArrayList<FavCoinModel>();
                    kur=FavCoinModel.getKur_state();


                    Database db=new Database(view.getContext());
                    db.openCon();

                    favcoin_list_a=db.ListByKurId(kur+"");
                    for(int a=0;a<favcoin_list_a.size();a++){
                        usdToCoin=ApiUtils.getAPIService();


                        usdToCoin.getSingleCoin("USDT",favcoin_list_a.get(a).getKoin_name()).enqueue(new Callback<SingleCoinBody>() {
                            @Override
                            public void onResponse(Call<SingleCoinBody> call, Response<SingleCoinBody> response) {
                                result_USDresponse.clear();
                                result_BTCresponse.clear();
                                result_ETHresponse.clear();
                                usdtcoinrecyler.getAdapter().notifyDataSetChanged();

                                favBody_usdt =response.body();
                                favResult_usdt = favBody_usdt.getSingleCoinResult();
                                favList_usdt.add(favResult_usdt);


                                favAdapter=new BtcAdapter(favList_usdt);
                                usdtcoinrecyler.setLayoutManager(mLayoutManager);
                                usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                                usdtcoinrecyler.setAdapter(favAdapter);
                                usdtcoinrecyler.invalidate();

                            }

                            @Override
                            public void onFailure(Call<SingleCoinBody> call, Throwable t) {

                            }
                        });

                    }


                }
                else if(FavCoinModel.getFav_state()==0){
                    topStar.setBackgroundResource(R.drawable.star_fill_gl);
                    favList_usdt.clear();
                    usdtcoinrecyler.getAdapter().notifyDataSetChanged();

                    int kur=FavCoinModel.getKur_state();
                    String kur_name="USDT";

                    usdToCoin=ApiUtils.getAPIService();
                    usdToCoin.getCoinWithType(kur_name).enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            usdt_response =response.body();
                            result_USDresponse= usdt_response.getResult();

                            usdtAdapter = new ResultAdapter(result_USDresponse);

                            usdtcoinrecyler.setLayoutManager(mLayoutManager);
                            usdtcoinrecyler.setItemAnimator(new DefaultItemAnimator());
                            usdtcoinrecyler.setAdapter(usdtAdapter);
                            usdtcoinrecyler.invalidate();




                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {

                        }
                    });


                }








            }
        });

    }





}


