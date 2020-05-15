package com.example.a05_userfragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.a05_userfragments.R
import com.example.a05_userfragments.models.UserModel
import com.example.a05_userfragments.activities.UsersActivity
import kotlinx.android.synthetic.main.fragment_user.view.*
import kotlinx.android.synthetic.main.fragment_user.view.idTextView

class UserFragment(private val activity: UsersActivity) : Fragment() {

    private lateinit var itemView: View
    lateinit var model: UserModel.Data

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemView = inflater.inflate(R.layout.fragment_user, container, false)
        init()
        return itemView
    }

    private fun init() {
        Glide.with(this)
            .load(model.avatar)
            .placeholder(R.mipmap.ic_launcher)
            .into(itemView.imageView)

        itemView.idTextView.text = model.id.toString()
        itemView.emailTextView.text = model.email
        itemView.firstNameTextView.text = model.firstName
        itemView.lastNameTextView.text = model.lastName

        itemView.setOnClickListener {
            activity.inspectUser(model.id)
        }
    }

}
