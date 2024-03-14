package com.conexa.conexachallenge.presentation.newsdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.databinding.FragmentNewsDetailBinding
import com.conexa.conexachallenge.presentation.base.BaseFragment
import com.conexa.conexachallenge.presentation.news.NewsViewModel
import com.conexa.conexachallenge.util.BundleKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>(
    FragmentNewsDetailBinding::inflate,
) {
    private val newsViewModel: NewsViewModel by viewModels()
    private var newsId: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
        initUi()
    }

    private fun getDataFromBundle() {
        newsId = arguments?.getInt(BundleKeys.NEW_ID) ?: 0
    }

    private fun initUi() {
        fetchNewsById()
        observeViewModel()
        initOnClicks()
    }

    private fun initOnClicks() {
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun fetchNewsById() {
        newsViewModel.getNewById(newsId)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                newsViewModel.uiState.collect { state ->
                    handleNewsById(state.newsById)
                    handleLoadingIndicator(state.loading)
                }
            }
        }
    }

    private fun handleNewsById(news: NewsByIdResponse?) {
        news?.let { news ->
            with(binding) {
                newsTitle.text = news.title
                newsDescription.text = news.content
                Glide.with(newsImage)
                    .load(news.image)
                    .into(newsImage)
            }
        }
    }


    private fun handleLoadingIndicator(loading: Boolean) {
            when(loading){
                true ->{
                    binding.progressBar.visibility=View.VISIBLE
                }
                false ->{
                    binding.progressBar.visibility=View.GONE
                }
            }
    }

}