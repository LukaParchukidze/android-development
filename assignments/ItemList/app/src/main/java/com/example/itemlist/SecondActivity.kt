package com.example.itemlist

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.itemlist.R
import kotlinx.android.synthetic.main.activity_second.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.hide()
        init()
    }

    private fun init() {
        addItemButtonSecond.setOnClickListener {
            addItem()
        }
    }

    private fun getCurrentDateTime(): String {
        val date = Date()

        return SimpleDateFormat.getDateTimeInstance(
            SimpleDateFormat.MEDIUM,
            SimpleDateFormat.MEDIUM
        ).format(date)
    }

    private fun addItem() {

        val currentDateTime = getCurrentDateTime()

        val itemModel = ItemModel(
            R.mipmap.content_image,
            titleEditText.text.toString(),
            descriptionEditText.text.toString(),
            currentDateTime
        )

        intent.putExtra("itemModel_Key", itemModel)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
