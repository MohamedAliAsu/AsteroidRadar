package com.udacity.asteroidradar.repository


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay

@Dao
interface AsteroidsDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg asteroid: Asteroid)
    @Query("select * from asteroids order by date(closeApproachDate) asc")
    fun getAllAsteroids():LiveData<List<Asteroid>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(pod:PictureOfDay)
    @Query("select * from pod limit 1")
    fun getImage():LiveData<PictureOfDay>
    @Query("delete from pod")
    fun deleteImage()
    @Query("delete frOm asteroids")
    fun clear()
    @Query("select * from asteroids where closeApproachDate = date(:today)")
    fun getTodayAsteroids(today:String=Constants.getToday()):LiveData<List<Asteroid>>
}