package com.example.myapplication.api

import com.example.myapplication.api.dto.DemoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DemoApiSave {

    @POST("demo/save")
    suspend fun saveMembers(
        @Body name:String
    ):Response<Any>
}

interface DemoApiGet {
    @GET("demo/get")
    suspend fun getMembers(
        //@Query("param1") param:Long
    ):Response <List<DemoDto>>
}