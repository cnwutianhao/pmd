package com.tyhoo.android.trad.pmd.data.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("newslist") val newsList: List<News>
)

data class News(
    @field:SerializedName("newsid") val newsId: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("postdate") val postDate: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("aid") val aid: String?
)