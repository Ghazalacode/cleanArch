package com.example.usecases

import android.app.Application
import android.arch.lifecycle.MutableLiveData


internal val applicationLiveData = MutableLiveData<Application>() // Module privte


// internal private to module
internal fun <T> MutableLiveData<T>.getNonNull() = value!!
internal fun MutableLiveData<Application>.getApplication() = value!!
object Domain {
    fun integrateWith( application: Application){
        applicationLiveData.value =application
    }
}