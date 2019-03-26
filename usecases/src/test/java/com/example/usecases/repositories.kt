package com.example.usecases

import com.example.entities.City
import com.example.entities.FavoriteCityId
import com.example.usecases.repositories.CitiesRepository

open class CitiesRepositoryMock : CitiesRepository {

    override fun searchCitiesByName(name: String): List<City> {
        return listOf()
    }

    override fun retrieveFavoriteCitiesIds(): List<Long> {
        return listOf()
    }

    override fun retrieveCitiesByIds(citiesIds: List<Long>): List<City> {
        return listOf()
    }

    override fun addFavoriteCityId(favoriteCityId: FavoriteCityId) {
    }

    override fun removeFavoriteCityId(favoriteCityId: FavoriteCityId) {
    }
}