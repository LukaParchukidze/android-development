package com.example.a05_userfragments.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.a05_userfragments.http_request.CustomCallback
import com.example.a05_userfragments.http_request.HttpRequest
import com.example.a05_userfragments.R
import com.example.a05_userfragments.models.UserModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        init()
    }

    private fun init(){

        val userId = intent.extras?.getString("userId")

        if (userId != null) {
            HttpRequest.getRequest(
                HttpRequest.USERS,
                userId,
                object :
                    CustomCallback {
                    override fun onSuccess(body: String) {
                        val listModel = Gson().fromJson(
                            body,
                            UserModel.User::class.java
                        ).data
                        firstNameTextView.text = listModel.firstName
                        lastNameTextView.text = listModel.lastName
                        emailTextView.text = listModel.email

                        Glide.with(this@UserProfileActivity)
                            .load(listModel.avatar)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(imageView)
                    }

                    override fun onFailed(error: String) {
                        Toast.makeText(this@UserProfileActivity,error,Toast.LENGTH_SHORT).show()
                    }
                })
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}
