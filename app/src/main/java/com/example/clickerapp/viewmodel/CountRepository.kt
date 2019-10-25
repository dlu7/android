package com.example.clickerapp.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.ibrahimsn.library.LiveSharedPreferences

class CountRepository(context: Context)
{
    //variables preferences and liveSharedPreferences initialized and cannot be changed
    // preferences saved to SharedPreferences
    // livedSharedPreferences saved to respective library
    private val preferences: SharedPreferences
    private val liveSharedPreferences: LiveSharedPreferences

    //initializer block that saves the preferences to shared preferences
    init
    {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        liveSharedPreferences = LiveSharedPreferences(preferences)
    }

    //function to pair number of clicks to the username entered in the beginning
    //  and will save to shared preferences
    fun setUserCount(name: String, count: Long)
    {
        preferences.edit().putLong(name, count).apply()
    }

    //function to get username from live data and pulls username from all the names that
    //  the emulator has saved
    fun getUserCount(name: String): LiveData<Long> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name), 0L)) {it[name]}

    //companion object initialized, including a private variable called PREFS to hold click counts
    companion object
    {
        private const val PREFS = "clickCounts"
    }

}
