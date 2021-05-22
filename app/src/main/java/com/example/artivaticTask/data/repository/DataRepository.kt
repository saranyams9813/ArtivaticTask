package com.example.artivaticTask.data.repository

import com.example.artivaticTask.apiService.ApiService
import com.example.artivaticTask.data.model.FeedResponse
import com.example.artivaticTask.utils.RetrofitBuilder

object DataRepository {
    val apiService: ApiService = RetrofitBuilder.getRetrofit().create(ApiService::class.java)

    suspend fun getFeed():FeedResponse {
      return apiService.getFeeds()
   }
}