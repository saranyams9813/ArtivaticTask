package com.example.artivaticTask.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.artivaticTask.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers


class HomeListViewmodel  : ViewModel(){
    fun getFeeds() = liveData(Dispatchers.IO) {
        try {
            var response = DataRepository.getFeed()
            emit(Result.success(response))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }
}