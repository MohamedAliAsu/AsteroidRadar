package com.udacity.asteroidradar.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.repository.AsteroidsDatabase
import com.udacity.asteroidradar.repository.Repository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(val context: Context) : ViewModel() {

    val picOfDay: LiveData<PictureOfDay>
        get() = _picOfDay
    var AsteroidsList = MediatorLiveData<List<Asteroid>>()

    val repository = Repository(AsteroidsDatabase.getInstance(context))


    init {

        initializeAsteroids()

        AsteroidsList.addSource(repository.asteroids) { AsteroidsList.value = it }
    }


    private var _picOfDay = repository.thePic
    private fun initializeAsteroids() {
        viewModelScope.launch {

            repository.refreshCache()

        }


    }

    fun todaysAsteroidsSelected() {
        AsteroidsList.removeSource(repository.asteroids)
        AsteroidsList.removeSource(repository.todayAsteroids)
        AsteroidsList.addSource(repository.todayAsteroids) { AsteroidsList.value = it }
    }

    fun defaultSelected() {
        AsteroidsList.removeSource(repository.asteroids)
        AsteroidsList.removeSource(repository.todayAsteroids)
        AsteroidsList.addSource(repository.asteroids) { AsteroidsList.value = it }
    }


}