package com.example.usecases.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.example.entities.City
import com.example.entities.FavoriteCityId
import com.example.usecases.CoordinatesTypeConverter
import com.example.usecases.applicationLiveData
import com.example.usecases.getApplication

// Global varaiable
val weatherDatabase by lazy {
    initializeDatabase(applicationLiveData.getApplication())
}

@Database(entities = [City::class , FavoriteCityId::class]
, version = 1  , exportSchema = false)
@TypeConverters(  CoordinatesTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase(){

    abstract val citiesDao: CitiesDao
    abstract val favoritesDao: FavoritesDao
}