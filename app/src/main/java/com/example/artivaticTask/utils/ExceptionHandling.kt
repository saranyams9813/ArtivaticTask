package com.example.artivaticTask.utils

import android.accounts.NetworkErrorException
import android.net.ConnectivityManager
import org.json.JSONException
import retrofit2.HttpException
import retrofit2.http.HTTP
import java.lang.Exception

object ExceptionHandling {

    fun Exception.getErrorMessage():String {
        var errMessage:String=this.message?:"Something went wrong"
       when(this){
           is HttpException->{errMessage= "Unable to fetch data , please try after some time"}
           is JSONException->{errMessage ="Something went wrong , we will fix it soon"}
       }
        return errMessage
    }
}