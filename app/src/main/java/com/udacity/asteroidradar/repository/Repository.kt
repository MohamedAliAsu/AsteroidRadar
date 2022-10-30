package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NasaApiService
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.*
import org.json.JSONObject
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Repository(private val db: AsteroidsDatabase) {
    var asteroids: LiveData<List<Asteroid>> = db.dao.getAllAsteroids()
    var thePic = db.dao.getImage()
    var todayAsteroids = db.dao.getTodayAsteroids()

    init {

        if (updateAsteroids()) {
            CoroutineScope(Dispatchers.Default).launch {
                db.dao.clear()
                refreshCache()
            }
        }

    }

    suspend fun refreshCache() {
        withContext(Dispatchers.IO) {
            try {
                db.dao.insert(*parseAsteroidsJsonResult(JSONObject(NasaApiService.retrofitService.getNasaResponse()
                )).toTypedArray())
                db.dao.insertImage(NasaApiService.retrofitService.getImage())
            } catch (e: Exception) {

            }

        }
    }

     fun updateAsteroids(): Boolean {
        val currentDate = Calendar.getInstance()
        val todaysAsteroid = db.dao.getTodayAsteroids().value?.get(0)?.closeApproachDate
        val asteroidsDateParsed = SimpleDateFormat("yyyy-MM-dd",
            Locale.getDefault()).parse((todaysAsteroid?:"2020-10-02"))
        val asteroidsDate = Calendar.getInstance()
        asteroidsDate.time = asteroidsDateParsed!!
        return if (currentDate.time.after(asteroidsDate.time)) {
            Log.i("a7a", "old")
            currentDate.time.after(asteroidsDate.time)


        }else false
    }
}