package com.example.havadurumuuygulamasi.Retrofit;


import com.example.havadurumuuygulamasi.Model.HavaDurumuSonucu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonApi {
    @GET("weather")
    Call<HavaDurumuSonucu>  getHavaDurumu(@Query("q") String sehir ,
                                               @Query("appid") String appid,
                                               @Query("units") String unit);
}
