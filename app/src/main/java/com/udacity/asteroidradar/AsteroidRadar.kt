package com.udacity.asteroidradar

import android.app.Application
import android.os.Build
import androidx.work.*
import com.udacity.asteroidradar.work.Refresher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Ref
import java.util.concurrent.TimeUnit

class AsteroidRadar : Application() {
    override fun onCreate() {
        super.onCreate()
        refreshAppData()
    }

    private fun refreshAppData() {
        CoroutineScope(Dispatchers.Default).launch {
            val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true).build()
            WorkManager.getInstance()
                .enqueueUniquePeriodicWork(Refresher.NAME, ExistingPeriodicWorkPolicy.KEEP,
                    PeriodicWorkRequestBuilder<Refresher>(1, TimeUnit.DAYS).setConstraints(
                        constraints).build())
        }
    }
}