package com.example.midtermn1.music

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midtermn1.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_band.*

class BandActivity : AppCompatActivity() {
    private lateinit var adapter: BandRecyclerViewAdapter
    private lateinit var band: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_band)
        init()
    }

    private fun init() {
        band = intent.extras?.getString("band", "").toString()
        val info = intent.extras?.getString("info", "")
        val songs = intent.getParcelableArrayListExtra<SongModel.Song>("songs")

        bandNameTextView.text = band
        bandInfoTextView.text = info


        bandRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BandRecyclerViewAdapter(songs!!, this)
        bandRecyclerView.adapter = adapter

    }

    fun inspectLyrics(title: String) {
        HttpRequestLyrics.getRequest(band, title, object : CustomCallback {
            override fun onSuccess(body: String) {
                val result = Gson().fromJson(body, LyricsModel::class.java)

                val intent = Intent(this@BandActivity, LyricsActivity::class.java)

                intent.putExtra("lyrics", result.lyrics)

                startActivity(intent)
            }

            override fun onFailed(error: String) {
                Toast.makeText(this@BandActivity, error, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
