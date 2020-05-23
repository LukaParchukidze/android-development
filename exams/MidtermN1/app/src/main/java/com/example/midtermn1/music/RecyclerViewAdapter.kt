package com.example.midtermn1.music

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.midtermn1.R
import kotlinx.android.synthetic.main.main_recyclerview_layout.view.*

class RecyclerViewAdapter(
    private val bands: ArrayList<BandModel>,
    private val activity: MainActivity
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.main_recyclerview_layout,
            parent,
            false
        )
    )

    override fun getItemCount() = bands.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var circularProgressDrawable = CircularProgressDrawable(activity)
        private lateinit var model: BandModel

        fun onBind() {
            model = bands[adapterPosition]


            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 50f

            circularProgressDrawable.start()

            Glide
                .with(activity)
                .load(model.imgUrl)
                .placeholder(circularProgressDrawable)
                .into(itemView.bandImageView)

            itemView.nameTextView.text = model.name

            itemView.setOnClickListener {
                activity.inspectBand(model.name, model.info)
            }
        }
    }
}