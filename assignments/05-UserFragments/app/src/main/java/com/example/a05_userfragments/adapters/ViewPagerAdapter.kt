package com.example.a05_userfragments.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.a05_userfragments.activities.UsersActivity
import com.example.a05_userfragments.fragments.UserFragment
import com.example.a05_userfragments.models.UserModel

class ViewPagerAdapter(fm: FragmentManager, private val items: MutableList<UserModel.Data>, private val activity: UsersActivity) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val fragment =
            UserFragment(activity)
        fragment.model = items[position]
        return fragment
    }

    override fun getCount() = items.size
}