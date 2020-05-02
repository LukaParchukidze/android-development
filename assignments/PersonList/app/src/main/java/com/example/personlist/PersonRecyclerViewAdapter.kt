package com.example.personlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.person_recyclerview_layout.view.*

class PersonRecyclerViewAdapter(
    private val people: ArrayList<PersonModel.Data>,
    private val mainActivity: MainActivity
) : RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.person_recyclerview_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = people.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var person: PersonModel.Data

        fun onBind() {
            person = people[adapterPosition]

            Glide.with(mainActivity).load(person.avatar).into(itemView.imageView)
            itemView.firstNameTextView.text = person.firstName
            itemView.lastNameTextView.text = person.lastName
            itemView.emailTextView.text = person.email

            itemView.setOnClickListener{
                mainActivity.inspectPerson(person.id)
            }
        }
    }
}
