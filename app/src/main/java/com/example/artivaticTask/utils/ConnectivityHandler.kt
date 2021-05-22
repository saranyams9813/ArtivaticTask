package com.example.artivaticTask.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.artivaticTask.ApplicationCommon


object ConnectivityHandler {
    fun isOnline(): Boolean {
       return isNetworkConnected()
    }
    private fun isNetworkConnected(): Boolean {
        val cm =
            ApplicationCommon.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}