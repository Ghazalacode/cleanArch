package com.example.usecases.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.entities.FavoriteCityId

@Dao
interface FavoritesDao{
    @Query( "  select * from FavoriteCityId")
    fun queryAll(): List<Long>

    @Insert
     fun  insert( favoriteCityId: FavoriteCityId)

    @Delete
     fun  delete( favoriteCityId: FavoriteCityId)


}