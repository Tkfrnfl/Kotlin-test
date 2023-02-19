package com.example.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.DemoApiGet
import com.example.myapplication.api.DemoApiSave
import com.example.myapplication.api.dto.DemoDto
import com.example.myapplication.api.service.DemoApiService
import com.example.myapplication.viewModel.DemoViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Member(val name: String)
class MemberList(val list: List<DemoDto>?)

val demoViewModel= module {
    viewModel{
        DemoViewModel()
    }
}

var job: Job? = null
/*val demoModule= module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(get())
            .baseUrl("http://192.168.219.105:8080/")
            .build()
            .create(DemoApiGet::class.java)
    }
}*/
/*val demoModule= module {
    single {
        //DemoViewModel().fetchMembers()
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            //onError("Exception: ${throwable.localizedMessage}")
        }
        // val members = MutableLiveData<List<DemoDto>>()

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = DemoApiService.getDemoService().getMembers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    MemberList(response.body())
                } else {
                    //onError("Error : ${response.message()}")
                }
            }
        }
    }
}*/
