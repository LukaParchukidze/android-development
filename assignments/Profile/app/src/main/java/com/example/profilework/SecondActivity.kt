package com.example.profilework

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.hide()
        init()
    }

    private fun init() {
        submitChangesButton.setOnClickListener {
            submitChanges()
        }
    }

    private fun submitChanges() {

        if(firstnameEditText.text.toString().isNotEmpty() && lastnameEditText.text.toString().isNotEmpty() && emailEditText.text.toString().isNotEmpty() && birthYearEditText.text.toString().isNotEmpty() && genderEditText.text.toString().isNotEmpty()) {
            val userModel = UserModel(
                firstnameEditText.text.toString(),
                lastnameEditText.text.toString(),
                emailEditText.text.toString(),
                birthYearEditText.text.toString().toInt(),
                genderEditText.text.toString()
            )

            intent.putExtra("userModel_Key", userModel)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }

    }
}
