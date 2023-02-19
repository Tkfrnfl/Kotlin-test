package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

import com.example.myapplication.R
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplication.Member
import com.example.myapplication.api.DemoApiGet
import com.example.myapplication.api.dto.DemoDto
import com.example.myapplication.demoViewModel
import com.example.myapplication.viewModel.DemoViewModel
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private  val viewModel:DemoViewModel by viewModel()
    //private  val list1:DemoApiGet by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameText:EditText=findViewById(R.id.et1)
        val tv1:TextView=findViewById(R.id.tv1)
        val button:Button=findViewById(R.id.btn1)
        startKoin {
            androidContext(this@MainActivity)
            //modules(demoModule)
            modules(demoViewModel)
        }
        viewModel.fetchMembers()
       button.setOnClickListener{
         CoroutineScope(Dispatchers.Main).launch{
             async { viewModel.save(nameText.text.toString()) }
             async {viewModel.fetchMembers()  }
             async { observeList()  }


         }


       }
        observeList()
    }
    fun observeList(){
        val tv1:TextView=findViewById(R.id.tv1)
        val nameText:EditText=findViewById(R.id.et1)
        viewModel.memberList.observe(this, Observer {memberList ->
            memberList?.let {
                var tmpString :String=""

                for(i in 0 until memberList.size){
                    Log.v("tt11", memberList[i].name)
                    tmpString= tmpString+memberList[i].name+","
                }
                tv1.text=tmpString

            }
        })
    }

}