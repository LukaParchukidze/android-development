package com.example.personlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_person.*

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        init()
    }

    private fun init() {
        val intent = intent

        val person = intent?.extras?.getParcelable("person") as PersonModel.Data

        intent.removeExtra("person")

        Glide.with(this).load(person.avatar).into(imageView)
        firstNameTextView.text = person.firstName
        lastNameTextView.text = person.lastName
        emailTextView.text = person.email

        backButton.setOnClickListener {
            finish()
        }
    }
}
