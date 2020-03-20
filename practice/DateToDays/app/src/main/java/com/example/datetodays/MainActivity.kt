package com.example.datetodays

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker = findViewById<DatePicker>(R.id.PickDate)
        val today = Calendar.getInstance()
        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
            , null
        )

    }

//    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//    val userInput = LocalDate.parse("$day-$month-$year", formatter)

        @RequiresApi(Build.VERSION_CODES.O)
        fun calculate(view: View) {
            val currentDate = LocalDate.now()

            if(currentDate > LocalDate.of(PickDate.year, PickDate.month + 1, PickDate.dayOfMonth)){
                ResultText.text = getString(R.string.result, ChronoUnit.DAYS.between(LocalDate.of(PickDate.year, PickDate.month + 1, PickDate.dayOfMonth), currentDate).toString())
            } else {
                ResultText.text = "Hello from Future ! :)"
            }
     }
}