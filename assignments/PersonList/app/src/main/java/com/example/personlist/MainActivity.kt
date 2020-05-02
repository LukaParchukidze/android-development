package com.example.personlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var people = ArrayList<PersonModel.Data>()
    private lateinit var adapter: PersonRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        DataLoader.getRequest("users", object : CustomCallback {
            override fun onSuccess(result: String) {
                val extra = Gson().fromJson(result, PersonModel::class.java).data

                people.addAll(extra)

                personRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = PersonRecyclerViewAdapter(people, this@MainActivity)
                personRecyclerView.adapter = adapter
            }
        })
    }

    fun inspectPerson(id: Int) {
        for (person in people) {
            if (person.id == id) {
                DataLoader.getRequest("users", id.toString(), object : CustomCallback {
                    override fun onSuccess(result: String) {
                        val intent = Intent(this@MainActivity, PersonActivity::class.java)

                        val extra = Gson().fromJson(result, PersonModel.Person::class.java).data

                        intent.putExtra("person", extra)

                        startActivity(intent)
                    }
                })
            }
        }
    }
}