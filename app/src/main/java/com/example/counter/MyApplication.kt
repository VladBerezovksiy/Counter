package com.example.counter

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyApplication : Application() {

    private lateinit var sharePrefs: SharedPreferences
    private val gson = Gson()

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharePrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    fun saveData(list: List<String>) {
        val listToSave = gson.toJson(list)
        sharePrefs.edit().putString("MyList", listToSave).commit()
    }

    fun getSaveData(): List<String> {
        val jsonList = sharePrefs.getString("MyList", "")
        var result = emptyList<String>()
        if (jsonList?.isNotEmpty() == true) {
            val type = object : TypeToken<List<String>>() { }.type
            result = gson.fromJson(jsonList, type)
        }
        return result
    }

    companion object {
        private lateinit var instance: MyApplication
        fun getApp() = instance
    }
}