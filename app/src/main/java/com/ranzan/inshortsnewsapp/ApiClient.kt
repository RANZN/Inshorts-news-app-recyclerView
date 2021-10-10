package com.ranzan.inshortsnewsapp

import android.text.Editable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("news")
    fun getData(@Query("category") category: Editable): Call<ResponseModel>
}