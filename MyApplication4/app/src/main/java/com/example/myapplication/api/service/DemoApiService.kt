package com.example.myapplication.api.service

import com.example.myapplication.api.DemoApiGet
import com.example.myapplication.api.DemoApiSave
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "http://192.168.219.105:8080/"

object DemoApiService{


    fun getDemoService():DemoApiGet{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DemoApiGet::class.java)
    }
    fun saveDemoService():DemoApiSave{
        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DemoApiSave::class.java)
    }
}