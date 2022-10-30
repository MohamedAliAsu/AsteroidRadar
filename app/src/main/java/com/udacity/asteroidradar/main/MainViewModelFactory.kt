package com.udacity.asteroidradar.main

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private val context: Context) :  ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(MainViewModel::class.java)){
           return MainViewModel(context) as T
       }
        else
            throw IllegalArgumentException("cannot")
    }
}