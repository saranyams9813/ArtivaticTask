package com.example.artivaticTask.utils

import com.example.artivaticTask.apiService.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private const val BASE_URL = "https://run.mocky.io/v3/"

     fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}