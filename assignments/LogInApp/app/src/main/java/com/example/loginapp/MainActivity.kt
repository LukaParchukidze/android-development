package com.example.loginapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        set_background()
        checker()
    }

    val calendar = Calendar.getInstance()
    val current_hour = calendar.get(Calendar.HOUR_OF_DAY)

    fun set_background(){
        if (current_hour in 6..10){
            backgroundImage.setImageResource(R.mipmap.background_morning)
            email_field.setHintTextColor(Color.WHITE)
            password_field.setHintTextColor(Color.WHITE)
        }
        else if (current_hour in 11..16){
            backgroundImage.setImageResource(R.mipmap.background_day)
        }
        else if (current_hour in 17..19) {
            backgroundImage.setImageResource(R.mipmap.background_noon)
            email_field.setHintTextColor(Color.WHITE)
            password_field.setHintTextColor(Color.WHITE)
        }
        else {
            backgroundImage.setImageResource(R.mipmap.background_night)
        }
    }

    private fun checker() {
        LogInButton.setOnClickListener {
            if(email_field.text.toString().isNullOrBlank() && password_field.text.toString().isNullOrBlank()) {
                Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}