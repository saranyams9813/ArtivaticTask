package com.example.artivaticTask.data.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class FeedResponse(val title:String?,
                        @SerializedName("rows")
                        val feedList:ArrayList<Feed>? )