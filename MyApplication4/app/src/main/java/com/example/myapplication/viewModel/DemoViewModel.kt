package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.dto.DemoDto
import com.example.myapplication.api.service.DemoApiService
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.dsl.viewModel

class DemoViewModel:ViewModel() {
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    val vmText="test"
    val members = MutableLiveData<DemoDto>()
    val memberList= MutableLiveData<List<DemoDto>>()
    val memberLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

/*    fun refresh(){
        fetchMembers()
    }*/

    fun save(name:String){
        postMembers(name)
    }

    fun fetchMembers(){
        loading.value=true

        job= CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response  = DemoApiService.getDemoService().getMembers()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    memberList.value=response.body()
                }
                else{
                    onError("Error : ${response.message()}")
                }
            }
        }
        Log.v("tft", memberList.value.toString())

    }
   private fun postMembers(name: String){
        loading.value=true

        job= CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response  = DemoApiService.saveDemoService().saveMembers(name)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    //members.value=response.body()
                    Log.v("tt",response.body().toString())
                }
                else{
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        memberLoadError.value = message
        loading.value = false
    }
}