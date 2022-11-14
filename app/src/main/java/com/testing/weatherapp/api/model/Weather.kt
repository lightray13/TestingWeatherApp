package com.testing.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Weather(
    val now: Int?,
    @SerializedName("now_dt") val nowDt: String?,
    val info: Info?,
    @SerializedName("geo_object") val geoObject: WeatherGeoObject?,
    val fact: Fact?,
    val forecasts: List<Forecast>?)

data class Info(
    val n: Boolean?,
    val geoid: Int?,
    val lat: Double?,
    val lon: Double?,
)

data class Fact(
    val temp: Int?,
    @SerializedName("feels_like") val feelsLike: Int?,
    val icon: String?,
    val condition: String?,
    @SerializedName("wind_speed") val windSpeed: Double?,
    @SerializedName("wind_dir") val windDir: String?,
    @SerializedName("pressure_mm") val pressureMm: Int?,
    @SerializedName("pressure_pa") val pressurePa: Int?,
    val humidity: Int?,
)

data class Forecast(
    val date: String?,
    @SerializedName("date_ts") val dateTs: Long?,
    val hours: List<Hour>?,
    val parts: Parts?
)

data class Hour(
    val hour: String?,
    @SerializedName("hour_ts") val hourTs: Int?,
    val temp: Int?,
    val icon: String?
)

data class Parts(
    @SerializedName("day_short") val dayShort: DayShort?
)

data class DayShort(
    val temp: Int?,
    val icon: String
)

data class WeatherGeoObject(
    val locality: Locality?,
)

data class Locality(
    val name: String?
)