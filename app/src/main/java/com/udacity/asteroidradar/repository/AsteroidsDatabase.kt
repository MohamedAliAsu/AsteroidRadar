package com.udacity.asteroidradar.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay

@Database(entities = [Asteroid::class, PictureOfDay::class], version = 2, exportSchema = false)
abstract class AsteroidsDatabase : RoomDatabase() {
    abstract val dao: AsteroidsDao


    companion object {
        @Volatile
        private lateinit var INSTANCE: AsteroidsDatabase
        fun getInstance(context: Context): AsteroidsDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(context,
                        AsteroidsDatabase::class.java,
                        "asteroids_database").fallbackToDestructiveMigration().build()
                }
                return INSTANCE
            }
        }
    }

}