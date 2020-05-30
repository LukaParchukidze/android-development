package com.example.a06_roomdatabase.activities

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.a06_roomdatabase.R
import com.example.a06_roomdatabase.adapters.RecyclerViewAdapter
import com.example.a06_roomdatabase.room_database.AppDatabase
import com.example.a06_roomdatabase.room_database.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_USER = 10
        const val EDIT_USER = 20
    }

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    val usersList = ArrayList<User>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        displayEmptyMessage()

        addItemButton.setOnClickListener {
            val intent = Intent(this, ChangeUserActivity::class.java)

            startActivityForResult(intent, ADD_USER)
        }

        recyclerViewId.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(
            usersList,
            this
        )
        recyclerViewId.adapter = adapter

    }

    fun openEditItemActivity(uid: Int, adapterPosition: Int) {
        val intent = Intent(this, ChangeUserActivity::class.java)
        intent.putExtra("uid", uid)
        intent.putExtra("adapterPosition", adapterPosition)

        startActivityForResult(intent, EDIT_USER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == ADD_USER) {
            AsyncTask.execute {
                val user = db.itemDao().getLastUser()
                runOnUiThread {
                    usersList.add(user)
                    adapter.notifyItemInserted(usersList.size - 1)
                    recyclerViewId.scrollToPosition(usersList.size - 1)
                }
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == EDIT_USER) {
            val uid = data!!.getIntExtra("uid", 0)
            val adapterPosition = data.getIntExtra("adapterPosition", 0)

            intent.removeExtra("uid")
            intent.removeExtra("adapterPosition")

            AsyncTask.execute {
                val getUser = db.itemDao().findById(uid)

                runOnUiThread {
                    usersList[adapterPosition].fistName = getUser.fistName
                    usersList[adapterPosition].lastName = getUser.lastName

                    adapter.notifyItemChanged(adapterPosition)
                }
            }
        }
    }

    fun displayEmptyMessage() {
        Toast.makeText(this, "No users exist", Toast.LENGTH_SHORT).show()
    }

}
