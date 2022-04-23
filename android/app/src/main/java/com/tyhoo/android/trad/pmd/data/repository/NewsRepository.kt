package com.tyhoo.android.trad.pmd.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tyhoo.android.trad.pmd.api.PMDService
import com.tyhoo.android.trad.pmd.data.response.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(private val service: PMDService) {

    suspend fun news(timestamp: String): LiveData<NewsResponse> {
        val newsResponse = MutableLiveData<NewsResponse>()
        val response = service.news(timestamp)
        newsResponse.postValue(response)
        return newsResponse
    }
}