package com.example.a05_userfragments.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import com.example.a05_userfragments.*
import com.example.a05_userfragments.adapters.ViewPagerAdapter
import com.example.a05_userfragments.http_request.CustomCallback
import com.example.a05_userfragments.http_request.HttpRequest
import com.example.a05_userfragments.models.UserModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {

    private val users = ArrayList<UserModel.Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        init()
    }

    private fun init() {
        HttpRequest.getRequest(
            HttpRequest.USERS,
            object : CustomCallback {
                override fun onSuccess(body: String) {
                    val userData = Gson().fromJson(
                        body,
                        UserModel::class.java
                    ).data
                    d("userData", "$userData")

                    users.addAll(userData)

                    viewPager.adapter =
                        ViewPagerAdapter(
                            supportFragmentManager,
                            users,
                            this@UsersActivity
                        )
                }
                override fun onFailed(error: String) {
                    Toast.makeText(this@UsersActivity,error, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun inspectUser(id: Int) {
        for (i in users) {
            if (i.id == id) {
                HttpRequest.getRequest(
                    HttpRequest.USERS,
                    id.toString(),
                    object :
                        CustomCallback {
                        override fun onSuccess(body: String) {
                            val intent = Intent(this@UsersActivity, UserProfileActivity::class.java)
                            intent.putExtra("userId", id.toString())
                            startActivity(intent)
                        }
                    })
            }
        }
    }
}
