package com.example.lab4_20206442.Service;

import com.example.lab4_20206442.Data.ClimaData;
import com.example.lab4_20206442.Data.GeoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoService {

    @GET("/geo/1.0/direct")
    Call<List<GeoData>> getGeo(@Query("q") String query, @Query("limit") int limit, @Query("appid") String appId);


}
