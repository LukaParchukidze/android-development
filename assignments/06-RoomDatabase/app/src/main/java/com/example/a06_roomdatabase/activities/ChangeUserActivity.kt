package com.example.a06_roomdatabase.activities

import android.app.Activity
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Room
import com.example.a06_roomdatabase.R
import com.example.a06_roomdatabase.room_database.AppDatabase
import com.example.a06_roomdatabase.room_database.User
import kotlinx.android.synthetic.main.activity_change_user.*

class ChangeUserActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_user)
        init()
    }

    private fun init() {

        // Add User
        addUserButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty())
                addUser()
            else
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }

        // Edit User
        if (intent.hasExtra("uid")) {
            showEditInfo()

            val adapterPosition = intent.getIntExtra("adapterPosition", 0)
            val uid = intent.getIntExtra("uid", 0)

            intent.removeExtra("adapterPosition")
            intent.removeExtra("uid")

            AsyncTask.execute {
                val getUser = db.itemDao().findById(uid)

                firstNameEditText.setText(getUser.fistName)
                lastNameEditText.setText(getUser.lastName)
            }

            editUserButton.setOnClickListener {
                val firstName = firstNameEditText.text.toString()
                val lastName = lastNameEditText.text.toString()

                if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                    AsyncTask.execute {
                        db.itemDao().update(
                            uid,
                            firstName,
                            lastName
                        )

                        intent.putExtra("uid", uid)
                        intent.putExtra("adapterPosition", adapterPosition)

                        setResult(Activity.RESULT_OK, intent)

                        finish()
                    }
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addUser() {
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()

        AsyncTask.execute {
            val user = User(
                "https://picsum.photos/200",
                firstName,
                lastName
            )
            db.itemDao().insertAll(user)
        }

        setResult(Activity.RESULT_OK)

        finish()
    }

    private fun showEditInfo() {
        createUserTextView.visibility = View.GONE
        addUserButton.visibility = View.GONE

        editUserTextView.visibility = View.VISIBLE
        editUserButton.visibility = View.VISIBLE
    }

}
