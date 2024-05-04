package com.example.lab4_20206442.Service;

import com.example.lab4_20206442.Data.ClimaData;
import com.example.lab4_20206442.Data.GeoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClimaService {


        @GET("data/2.5/weather")
        Call<List<ClimaData>> getClima(
                @Query("lat") double latitud,
                @Query("lon") double longitud,
                @Query("appid") String appId
        );


}
