package com.example.midtermn1.music

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.midtermn1.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val bands = ArrayList<BandModel>()
    private val bandData = ArrayList<SongModel.Data>()

    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        HttpRequest.getRequest(HttpRequest.BANDS, object : CustomCallback {
            override fun onSuccess(body: String) {
                val result = Gson().fromJson(body , Array<BandModel>::class.java).toList()

                bands.addAll(result)

                recyclerViewId.layoutManager =
                    GridLayoutManager(this@MainActivity, 1, GridLayoutManager.HORIZONTAL, false)
                adapter = RecyclerViewAdapter(bands, this@MainActivity)
                recyclerViewId.adapter = adapter

                bandTextView.visibility = View.VISIBLE
            }

            override fun onFailed(error: String) {
                parseErrorBody(error)
            }
        })
    }

    private fun parseErrorBody(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    fun inspectBand(name: String?, info: String?) {
        HttpRequest.getRequest(HttpRequest.SONGS, object : CustomCallback {
            override fun onSuccess(body: String) {
                val result = Gson().fromJson(body, SongModel::class.java)

                bandData.addAll(result.data)

                var bandName = ""
                var bandInfo = ""
                var bandSongs = ArrayList<SongModel.Song>()

                for (band in bandData) {
                    if (band.band == name) {
                        bandName = band.band
                        bandInfo = info!!
                        bandSongs = band.songs
                    }
                }

                val intent = Intent(this@MainActivity, BandActivity::class.java)

                intent.putExtra("band", bandName)
                intent.putExtra("info", bandInfo)
                intent.putParcelableArrayListExtra("songs", bandSongs)

                startActivity(intent)
            }

            override fun onFailed(error: String) {
                parseErrorBody(error)
            }
        })
    }
}