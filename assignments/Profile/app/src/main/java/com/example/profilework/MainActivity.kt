package com.example.profilework

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        init()
    }

    private fun init() {
        changeProfileButton.setOnClickListener {
            changeProfileCredentials()
        }
    }

    private fun changeProfileCredentials() {
        val intent = Intent(this, SecondActivity::class.java)

        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            val userModel = data!!.getParcelableExtra("userModel_Key") as UserModel

            fullNameMain.text = userModel.parcel_firstname + " " + userModel.parcel_lastname
            emailMain.text = userModel.parcel_email
            birthYearMain.text = userModel.parcel_birthYear.toString()
            genderMain.text = userModel.parcel_gender
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}

