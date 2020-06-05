package com.example.a07_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class Test : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
//        runButton.setOnClickListener {
//            CoroutineScope(IO).launch {
//                getApiRequest()
//            }
//        }
    }

    private fun setText(result: String) {
//        CoroutineScope(Main).launch {
//            textView.text = result
//        }
    }

    private suspend fun getApiRequest() {
//        d("getUsers", "getUsers")
//        val result1 = request1()
//        d("result", result1)
//        val result2 = request2()
//        d("result", result2)
//
//        setText(result2)

        val job = withTimeoutOrNull(3500) {
            val result1 = request1()
            d("result", result1)
            val result2 = request2()
            d("result", result2)

            setText(result2)
        }

        if(job == null){
            d("jobStatus","job is not complete")
        }
    }

    private suspend fun request1(): String {
        val result1 = "result1"
        delay(1000)
        return result1
    }

    private suspend fun request2(): String {
        val result2 = "result2"
        delay(2000)
        return result2
    }
}
