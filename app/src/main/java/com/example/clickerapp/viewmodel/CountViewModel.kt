package com.example.clickerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.clickerapp.viewmodel.CountRepository

class CountViewModel(application: Application): AndroidViewModel(application)
{
    //private variable repository initialized and gets context of application
    private val repository = CountRepository(application.applicationContext)

    //function is called from variable repository so variable can pull information
    //  from ViewModel
    fun getUserCount(name: String) = repository.getUserCount(name)

    //repository calls function to get list of users in ViewModel
    fun setUserCount(name: String, count: Long) = repository.setUserCount(name, count)
}