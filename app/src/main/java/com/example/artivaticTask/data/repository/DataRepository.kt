package com.example.artivaticTask.data.repository

import android.accounts.NetworkErrorException
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.artivaticTask.apiService.ApiService
import com.example.artivaticTask.data.model.FeedResponse
import com.example.artivaticTask.utils.ConnectivityHandler
import com.example.artivaticTask.utils.RetrofitBuilder
import java.lang.Exception


object DataRepository {
    val apiService: ApiService = RetrofitBuilder.getRetrofit().create(ApiService::class.java)
    var feedResponse:FeedResponse?=null

    suspend fun getFeed():FeedResponse {
        feedResponse?.let { return it }
        if(!ConnectivityHandler.isOnline())
            throw Exception("Please connect to internet")
      return apiService.getFeeds()
   }

}