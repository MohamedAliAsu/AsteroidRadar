package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.repository.AsteroidsDatabase.Companion.getInstance
import com.udacity.asteroidradar.repository.Repository
import retrofit2.HttpException

class Refresher(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    companion object{
        const val NAME="refresher"
    }
    override suspend fun doWork(): Result {
        val repo = Repository(getInstance(applicationContext))
        return try {
            getInstance(applicationContext).dao.clear()
            getInstance(applicationContext).dao.deleteImage()
            repo.refreshCache()
            Result.success()
        }catch (e:HttpException){
            Result.retry()
        }
    }
}