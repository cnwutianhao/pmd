package com.tyhoo.android.compose.pmd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.compose.pmd.api.PMDService
import com.tyhoo.android.compose.pmd.data.News
import com.tyhoo.android.compose.pmd.data.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val service: PMDService
) : ViewModel() {

    private val _newsList = MutableStateFlow(NewsResponse(newsList = listOf()))
    val newsList: StateFlow<NewsResponse>
        get() = _newsList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            val timestamp = System.currentTimeMillis().toString()
            val response = service.news(timestamp)

            val nonAdNewsList: MutableList<News> = mutableListOf()
            response.newsList.mapIndexed { _, news ->
                if (news.aid.isNullOrEmpty()) {
                    nonAdNewsList.add(news)
                }
            }

            _newsList.emit(NewsResponse(nonAdNewsList))
        }
    }
}