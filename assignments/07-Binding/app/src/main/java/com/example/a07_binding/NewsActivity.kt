package com.example.a07_binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a07_binding.databinding.ActivityNewsBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private val news = ArrayList<NewsModel>()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_news
            )

        init()
    }

    private fun init() {

        recyclerView.layoutManager =
            LinearLayoutManager(this@NewsActivity, LinearLayoutManager.VERTICAL, false)

        swipeRefreshLayout.isRefreshing = true

        fetchNews()

        swipeRefreshLayout.setOnRefreshListener {
            clearData()

            swipeRefreshLayout.isRefreshing = true

            fetchNews()

        }

    }

    private fun fetchNews() {
        HttpRequest.getRequest(HttpRequest.NEWS, object : CustomCallback {
            override fun onSuccess(body: String) {
                val result = Gson().fromJson(body, Array<NewsModel>::class.java).toList()

                news.addAll(result)

                adapter = RecyclerViewAdapter(news)
                recyclerView.adapter = adapter

                swipeRefreshLayout.isRefreshing = false
            }

            override fun onFailed(error: String) {
                parseErrorBody(error)

                swipeRefreshLayout.isRefreshing = false
            }
        })


    }

    private fun parseErrorBody(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun clearData(){
        news.clear()
        adapter.notifyDataSetChanged()
    }

}
