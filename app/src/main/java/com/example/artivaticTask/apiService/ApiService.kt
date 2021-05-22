package com.example.artivaticTask.apiService

import com.example.artivaticTask.data.model.Feed
import com.example.artivaticTask.data.model.FeedResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    suspend fun getFeeds(): FeedResponse
}