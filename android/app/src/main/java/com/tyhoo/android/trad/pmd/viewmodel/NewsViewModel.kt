package com.tyhoo.android.trad.pmd.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.tyhoo.android.trad.pmd.adapter.NewsAdapter
import com.tyhoo.android.trad.pmd.data.repository.NewsRepository
import com.tyhoo.android.trad.pmd.data.response.News
import com.tyhoo.android.trad.pmd.data.response.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    suspend fun requestData(owner: LifecycleOwner, newsAdapter: NewsAdapter) {
        newsObserver(owner, newsAdapter)
    }

    private suspend fun newsObserver(
        owner: LifecycleOwner, newsAdapter: NewsAdapter
    ) = Observer<NewsResponse> { data ->
        // 将列表里所有非广告的数据保存到新列表里
        val nonAdNewsList: MutableList<News> = mutableListOf()
        data.newsList.mapIndexed { _, news ->
            if (news.aid.isNullOrEmpty()) {
                nonAdNewsList.add(news)
            }
        }

        // 通知 ListAdapter 更新 Adapter
        newsAdapter.submitList(nonAdNewsList)
    }.apply {
        val timestamp = System.currentTimeMillis().toString()
        news(timestamp).observe(owner, this)
    }

    private suspend fun news(timestamp: String): LiveData<NewsResponse> {
        return repository.news(timestamp)
    }
}