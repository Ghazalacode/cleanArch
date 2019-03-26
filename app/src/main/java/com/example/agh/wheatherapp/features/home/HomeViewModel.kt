package com.example.agh.wheatherapp.features.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.entities.City
import com.example.usecases.CitiesResult
import com.example.usecases.SearchCityByNameUseCase
import com.example.usecases.engine.toMutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.io.Serializable

// injecting the ui related LD's and usecases in the constructors
class HomeViewModel(
        val showCityForecast: PublishSubject<Serializable> = PublishSubject.create(),
        val searchProgress: MutableLiveData<Boolean> = false.toMutableLiveData(),
        val citiesResult: CitiesResult = ArrayList<City>().toMutableLiveData(),
        private val disposables: CompositeDisposable = CompositeDisposable(),
        private val searchCityByName: SearchCityByNameUseCase = SearchCityByNameUseCase(
                searchProgress,
                citiesResult
        )) : ViewModel(){

    fun onSearchButtonClicked(cityName: String?) {
        Observable.fromCallable { searchCityByName(cityName) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .also { disposables.add(it) }
}

    override fun onCleared() {
        super.onCleared()
         disposables.dispose()

    }


}