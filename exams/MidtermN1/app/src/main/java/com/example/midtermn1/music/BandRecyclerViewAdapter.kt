package com.example.midtermn1.music

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermn1.R
import kotlinx.android.synthetic.main.band_recyclerview_layout.view.*

class BandRecyclerViewAdapter(
    private val songs: ArrayList<SongModel.Song>,
    private val activity: BandActivity
) :
    RecyclerView.Adapter<BandRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.band_recyclerview_layout,
            parent,
            false
        )
    )

    override fun getItemCount() = songs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var model: SongModel.Song

        fun onBind() {
            model = songs[adapterPosition]

            itemView.titleTextView.text = model.title

            itemView.setOnClickListener {
                activity.inspectLyrics(model.title)
            }
        }
    }
}