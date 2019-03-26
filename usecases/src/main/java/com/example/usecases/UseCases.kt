package com.example.usecases

import android.arch.lifecycle.ComputableLiveData
import android.arch.lifecycle.MutableLiveData
import android.speech.RecognizerResultsIntent
import com.example.entities.City
import com.example.usecases.repositories.CitiesRepository
import com.example.usecases.repositories.cittiesRepository


// functions are imported like classes in kotlin
// so the're not expensive
fun randomNumberGenerator ()  = Math.random() * 1000

fun numberIncrementer( numberLiveData: MutableLiveData<Int> , incrementBy :Int =1 ){

    val oldValue  = numberLiveData.value ?:0
    numberLiveData.postValue(oldValue +incrementBy)
}


fun changeButtonState( stateLD: MutableLiveData<Boolean> ){


}

fun changeStateAndIncrement(numberLiveData: MutableLiveData<Int>
                            ,  stateLD: MutableLiveData<Boolean> ){

    val oldValue  = stateLD.value?:false
   if (oldValue) numberIncrementer(numberLiveData)
    stateLD.postValue(!oldValue)

}


// usecases in backgroung don't worry here about swithching threads


// usecase 1 : search city by name
// if is searching, then do not trigger action
// city name must not be null
// if all is OK, trigger search


typealias CitiesResult = MutableLiveData<List<City>>


class SearchCityByNameUseCase(
        private val searching: MutableLiveData<Boolean>,
        private val result: CitiesResult,
        private val repository: CitiesRepository = cittiesRepository
) {
   operator fun invoke(cityName: String?) {

        // old java logic

/*     if (  ! searchName.isBlank() && !searchStateLD.value ){
         searchName.takeIf { searchStateLD }
        val searchResults =  cittiesRepository.searchCittiesByName(searchName)
         results.postValue(searchResults)
         searchStateLD.postValue(!searchStateLD.value)
     }else {
     }*/


        cityName
                ?.takeUnless { searching.value ?: false }
                ?.also { searching.postValue(true) }
                ?.let { repository.searchCitiesByName(it) }
                ?.also { result.postValue(it) }
                ?.also { searching.postValue(false) }
    }
}

// usecase 2 : retrieve favorite cities ids (longs)
// if is retrieving, then do not trigger action
// if favorites is empty, throw an exception
// if favorites not empty, convert them to ids (longs)

fun retrieveFavoritesIds(  searchStateLD: MutableLiveData<Boolean>
                           , results : MutableLiveData<List<Long>>
                           , repository: CitiesRepository = cittiesRepository){

    val isLoading = searchStateLD.value
    isLoading?.takeUnless { it }
            ?.also { searchStateLD.postValue(true)  }
            ?.run { repository.retrieveFavoriteCitiesIds() }
            ?.also { results.postValue(it)  }
            ?.also { searchStateLD.postValue(false) }



}



// usecase 3 : retrieve cities by Ids
// if is retrieving, then do not trigger action
// if all is Ok, trigger action

   fun retrieveCitiesByIds(
          ids: List<Long>?,
           searchStateLD: MutableLiveData<Boolean>
          , results : MutableLiveData<List<City>>
          , repository: CitiesRepository = cittiesRepository){

       val isLoading  = searchStateLD.value
       ids
         ?.takeUnless { it.isNotEmpty() }
               ?.takeUnless {isLoading?:false }
               ?.also { searchStateLD.postValue(true)  }
               ?.let { repository.retrieveCitiesByIds(it) }
               ?.also { results.postValue(it)  }
               ?.also { searchStateLD.postValue(false) }

   }


