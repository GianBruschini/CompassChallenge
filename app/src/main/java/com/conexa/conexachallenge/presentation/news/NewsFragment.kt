package com.conexa.conexachallenge.presentation.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.conexa.conexachallenge.data.api.model.response.News
import com.conexa.conexachallenge.databinding.FragmentNewsBinding
import com.conexa.conexachallenge.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(
    FragmentNewsBinding::inflate,
) {

    private val viewModel: NewsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeViewModel()
        fetchNews()
    }

    private fun initUi() {

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { state ->
                    handleNewsList(state.news)
                    handleLoadingIndicator(state.loading)
                    handleUserMessage(state.userMessage)
                }
            }
        }
    }

    private fun handleNewsList(news: List<News>) {
        news
    }

    private fun handleUserMessage(loading: String?) {

    }
    private fun handleLoadingIndicator(loading: Boolean) {
        // Aquí puedes manejar el indicador de carga
    }

    private fun fetchNews() {
        viewModel.fetchNews()
    }
}
