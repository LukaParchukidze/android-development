package com.example.itemlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 10

    val main_items = ArrayList<ItemModel>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setStaticItems()
        init()
    }

    private fun init() {
        addItemButtonMain.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            startActivityForResult(intent, REQUEST_CODE)
        }

        recyclerViewId.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(main_items, this)
        recyclerViewId.adapter = adapter
    }

    private fun setStaticItems() {
        main_items.add(
                ItemModel(
                        R.mipmap.content_image,
                        "Title1",
                        "Description1",
                        "10 Apr 2020 00:00:00"
                )
        )
        main_items.add(
                ItemModel(
                        R.mipmap.content_image,
                        "Title2",
                        "Description2",
                        "10 Apr 2020 00:00:00"
                )
        )
        main_items.add(
                ItemModel(
                        R.mipmap.content_image,
                        "Title3",
                        "Description3",
                        "10 Apr 2020 00:00:00"
                )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            val itemModel = data!!.getParcelableExtra("itemModel_Key") as ItemModel

            main_items.add(
                    ItemModel(
                            itemModel.image,
                            itemModel.title,
                            itemModel.description,
                            itemModel.dateString
                    )
            )
            adapter.notifyItemInserted(main_items.size - 1)
            recyclerViewId.scrollToPosition(main_items.size - 1)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
