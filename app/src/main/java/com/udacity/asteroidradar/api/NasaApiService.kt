package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
interface serverAccess{
    @GET("neo/rest/v1/feed")
    suspend fun getNasaResponse(@Query("api_key") api:String = Constants.API_KEY
    ):String
    @GET("planetary/apod")
    suspend fun getImage(@Query("api_key") api:String =Constants.API_KEY):PictureOfDay
}
object NasaApiService{
    val retrofitService:serverAccess by lazy {
        retrofit.create(serverAccess::class.java)
    }
}