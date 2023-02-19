package com.example.myapplication.api.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DemoDto (
    @SerializedName("id")
    @Expose
    var id:Long,

    @SerializedName("name")
    @Expose
    var name:String,

    )

