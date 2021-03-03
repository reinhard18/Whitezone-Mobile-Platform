package com.whitezone.whitezone.API

import com.whitezone.whitezone.Model.ModelDataIndonesia
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("indonesia")
    fun getIndonesia(): Call<ArrayList<ModelDataIndonesia>>
}