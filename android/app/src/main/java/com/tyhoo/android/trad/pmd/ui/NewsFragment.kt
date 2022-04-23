package com.tyhoo.android.trad.pmd.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.tyhoo.android.trad.pmd.adapter.NewsAdapter
import com.tyhoo.android.trad.pmd.databinding.FragmentNewsBinding
import com.tyhoo.android.trad.pmd.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var newsBinding: FragmentNewsBinding

    private var requestDataJob: Job? = null

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 将布局视图绑定到架构组件
        // https://developer.android.google.cn/topic/libraries/data-binding/architecture
        newsBinding = FragmentNewsBinding.inflate(inflater, container, false)
        newsBinding.lifecycleOwner = viewLifecycleOwner
        newsBinding.viewModel = newsViewModel

        return newsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化 Adapter
        val newsAdapter = NewsAdapter()
        newsBinding.newsList.adapter = newsAdapter
        newsBinding.newsList.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        // 在 ViewModel 里请求数据
        requestData(newsAdapter)
    }

    private fun requestData(newsAdapter: NewsAdapter) {
        requestDataJob?.cancel()
        requestDataJob = lifecycleScope.launchWhenResumed {
            newsViewModel.requestData(viewLifecycleOwner, newsAdapter)
        }
    }
}