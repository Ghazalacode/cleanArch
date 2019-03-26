package com.example.usecases.database

import android.arch.persistence.room.TypeConverter
import com.example.entities.Coordinates
import com.google.gson.Gson

class CoordinatesTypeConverter {

    @TypeConverter
    fun toJson(coordinates: Coordinates) = Gson().toJson(coordinates)

    @TypeConverter
    fun fromJson(string: String) = Gson().fromJson(string, Coordinates::class.java)
}