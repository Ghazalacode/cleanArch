package com.example.usecases.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.entities.City

@Dao
interface CitiesDao{
    @Query( "  select * from City where City.name Like '%' || :name ||'%'")
    fun queryCitiesByName(name: String): List<City>
    @Query( "  select * from city where city.id in ( :ids) ")
    fun queryCitiesByIds(ids: List<Long>): List<City>
}