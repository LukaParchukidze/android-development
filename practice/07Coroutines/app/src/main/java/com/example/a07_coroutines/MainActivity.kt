package com.example.a07_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private lateinit var job: Job

    companion object {
        private const val MIN = 0
        private const val MAX = 60
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initJob()
    }

    private fun initJob() {
        job = Job()

        startTimerButton.setOnClickListener {
            if (::job.isInitialized || job.isCancelled || job.isCompleted) {
                job = CoroutineScope(IO).launch {
                    for (second in MIN..MAX) {
                        delay(100)
                        CoroutineScope(Main).launch {
                            textView.text = second.toString()
                        }
                    }
                    job.invokeOnCompletion {
                        var message = it?.message
                        if (message == null) {
                            message = "job is cancelled"
                        }

                        showMessage(message)
                    }

                    job.cancel()
                }
                startTimerButton.isClickable = false
            }
        }

        resetTimerButton.setOnClickListener {
            job.cancel(CancellationException("Timer is reset"))
            startTimerButton.isClickable = true
        }
    }

    private fun showMessage(message: String) {
        CoroutineScope(Main).launch {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
