package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val API_KEY="UJf1GRtEEBERhiFQPZNCD7dZizxvr3yggMnBUhdA"

    fun getToday():String{
        return (SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())).format(Date())
    }
}