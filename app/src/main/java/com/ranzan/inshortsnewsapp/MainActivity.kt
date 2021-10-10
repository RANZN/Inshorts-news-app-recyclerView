package com.ranzan.inshortsnewsapp

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var list: List<DataItem> = listOf<DataItem>()
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var s = searchTitle.text
        search.setOnClickListener {
            if (s.length > 3) {
                callApi()
            }
        }
    }

    private fun callApi() {
        val apiClient = Network.getRetrofitInstance().create(ApiClient::class.java)
        apiClient.getData(searchTitle.text).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                list = response.body()?.data as List<DataItem>
                setRecyclerView()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed", LENGTH_SHORT).show()
            }

        })
    }

    private fun setRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter(list)
        val linearLayout = LinearLayoutManager(this)
        recyclerView.run {
            adapter = recyclerViewAdapter
            layoutManager = linearLayout
        }
    }
}