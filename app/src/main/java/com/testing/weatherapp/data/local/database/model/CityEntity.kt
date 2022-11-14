package com.testing.weatherapp.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_list")
data class CityEntity(
    @PrimaryKey val name: String,
    val lat: String?,
    val lon: String?
)