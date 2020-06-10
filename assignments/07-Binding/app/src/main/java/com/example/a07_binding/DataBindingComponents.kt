package com.example.a07_binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_recyclerview_layout.view.*

object DataBindingComponent {

    @JvmStatic
    @BindingAdapter("setResource")
    fun setImage(imageView: ImageView, image: String) {
        val circularProgressDrawable = CircularProgressDrawable(imageView.context)

        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 50f

        circularProgressDrawable.start()

        Glide.with(imageView)
            .load(image)
            .placeholder(circularProgressDrawable)
            .into(imageView.imageView)
    }
}