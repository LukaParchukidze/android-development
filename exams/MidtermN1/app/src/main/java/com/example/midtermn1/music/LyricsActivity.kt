package com.example.midtermn1.music

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.midtermn1.R
import kotlinx.android.synthetic.main.activity_lyrics.*

class LyricsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyrics)
        init()
    }

    private fun init() {
        val lyrics = intent.getStringExtra("lyrics")

        lyricsTextView.text = lyrics
    }
}
