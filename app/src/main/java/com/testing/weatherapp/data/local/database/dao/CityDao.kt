package com.testing.weatherapp.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testing.weatherapp.data.local.database.model.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM city_list")
    fun cityList(): LiveData<List<CityEntity>>

    @Query("SELECT * FROM city_list WHERE name = :name")
    suspend fun cityEntityByName(name: String): CityEntity?

    @Query("SELECT * FROM city_list WHERE name = :name")
    fun cityEntityLiveDataByName(name: String): LiveData<CityEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityEntity(data: CityEntity): Long

    @Delete
    suspend fun deleteCityEntity(data: CityEntity): Int
}