package com.example.a1sep_coroutine_intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    val TAG = "Coroutine_tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // launching co-routine
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "From Launch")
            var data = getDataFromAPI()
            Log.d(TAG, "${data}")

            val d = async {
                val data = getDataFromAPI()
                return@async data
            }.await()

            val d2 = async {
                var d1 = "yes"
                val data = getDataFromAPI()
                return@async d
            }.await()

            CoroutineScope(Dispatchers.Main).launch {
                tvName.text = "$d2 "
            }
        }









    }

    // only called from coroutine and shows error while
    suspend fun getDataFromAPI(): String {
        delay(3000)
        return "Api Response arrived"
    }

}

//        coroutine builders:- launch,async,awake,run-blocking
//        Log.d("${Thread.currentThread().name}","work in Main thread started")
//
//        GlobalScope.launch {
//            Log.w("${Thread.currentThread().name}", "Fake work in couruotine started " )
//            Thread.sleep(1500)
//            Log.w("${Thread.currentThread().name}", "Fake work in couruotine ended " )
//        }
//
//        Thread.sleep(4000)
//        Log.d("${Thread.currentThread().name}","work in Main thread started")

