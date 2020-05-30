package com.example.a06_roomdatabase.adapters

import android.os.AsyncTask
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.example.a06_roomdatabase.activities.MainActivity
import com.example.a06_roomdatabase.R
import com.example.a06_roomdatabase.room_database.User
import kotlinx.android.synthetic.main.item_recyclerview_layout.view.*

class RecyclerViewAdapter(
    private val usersList: ArrayList<User>,
    private val activity: MainActivity
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_recyclerview_layout,
            parent,
            false
        )
    )

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var model: User
        private val circularProgressDrawable = CircularProgressDrawable(activity)

        fun onBind() {
            model = usersList[adapterPosition]

            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f

            circularProgressDrawable.start()

            Glide.with(activity)
                .load(model.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .apply(RequestOptions().signature(ObjectKey("${System.currentTimeMillis()}")))
                .placeholder(circularProgressDrawable)
                .into(itemView.imageView)

            itemView.firstNameTextView.text = model.fistName
            itemView.lastNameTextView.text = model.lastName

            itemView.editUserButton.setOnClickListener {
                activity.openEditItemActivity(model.uid, adapterPosition)
            }

            itemView.setOnLongClickListener {
                activity.usersList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)

                AsyncTask.execute {
                    activity.db.itemDao().delete(model)
                }

                if (activity.usersList.size == 0) {
                    activity.displayEmptyMessage()
                }

                true
            }
        }
    }
}