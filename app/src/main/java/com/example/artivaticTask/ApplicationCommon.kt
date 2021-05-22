package com.example.artivaticTask

import android.app.Application
import android.content.Context

class ApplicationCommon : Application(){
    companion object{
        lateinit var appContext:Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}