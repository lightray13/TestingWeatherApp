package com.testing.weatherapp.api

import com.testing.weatherapp.api.model.Coordinates
import com.testing.weatherapp.api.model.Weather
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("X-Yandex-API-Key: a7968853-9cdd-4db3-935b-20bc8bf02fd0")
    @GET("/v2/forecast")
    suspend fun weatherList(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("lang") lang: String
    ): Response<Weather>

    @GET
    suspend fun getCityCoordinates(
        @Url url: String,
        @Query("apikey") apikey: String,
        @Query("geocode") geocode: String,
        @Query("format") format: String
    ): Response<Coordinates>
}