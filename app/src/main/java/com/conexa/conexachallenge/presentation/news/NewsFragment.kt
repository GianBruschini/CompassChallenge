package com.conexa.conexachallenge.presentation.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.conexa.conexachallenge.R
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.databinding.FragmentNewsBinding
import com.conexa.conexachallenge.presentation.adapters.NewsAdapter
import com.conexa.conexachallenge.presentation.base.BaseFragment
import com.conexa.conexachallenge.util.BundleKeys
import com.conexa.conexachallenge.util.NetworkUtils
import com.conexa.conexachallenge.util.hideKeyboard
import com.conexa.conexachallenge.util.showCustomToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(
    FragmentNewsBinding::inflate,
), NewsAdapter.OnItemClickListener {
    private var listOfNews: ArrayList<NewsResponse>? = null
    private val viewModel: NewsViewModel by viewModels()
    private var newsAdapter: NewsAdapter = NewsAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        initRv()
        observeViewModel()
        fetchNews()
        initOnClicks()
        setSearchLogic()
    }

    private fun setSearchLogic() {
        binding.searchEdtxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filterNews(s.toString())
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterNews(query: String) {
        val filteredList = listOfNews?.filter { news ->
            news.title.contains(query, true) || news.content.contains(query, true)
        }


        if (filteredList != null) {
            newsAdapter.setList(filteredList as ArrayList<NewsResponse>)
            newsAdapter.notifyDataSetChanged()
        }
    }



    private fun initRv() {
        with(binding.newsRv) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = newsAdapter
            newsAdapter.setOnItemClickListener(this@NewsFragment)
        }
    }


    private fun initOnClicks() {
        binding.btnUsers.setOnClickListener {
            hideKeyboard()
            findNavController().navigate(R.id.action_newsFragment_to_usersFragment)
        }
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

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    private fun fillNewsRv(newsList: List<NewsResponse>) {
        val arrayListOfNews = ArrayList(newsList)
        newsAdapter.setList(arrayListOfNews)
        newsAdapter.notifyDataSetChanged()
    }

    private fun handleNewsList(newsList: List<NewsResponse>) {
        if (newsList.isEmpty()) {
            binding.noResultsTxt.visibility = View.GONE
        } else {
            binding.noResultsTxt.visibility = View.GONE
            newsList.let {
                this.listOfNews = it as ArrayList<NewsResponse>
                fillNewsRv(it)
            }
        }
    }

    private fun handleUserMessage(msg: String?) {
        if(msg!=null){
            ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gradient_error)
                ?.let { drawable ->
                    Toast(requireContext()).showCustomToast(
                        msg.toString(),
                        drawable,
                        requireActivity()
                    )
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

    private fun fetchNews() {
        viewModel.fetchNews()
    }

    override fun onitemClick(position: Int) {
        if(NetworkUtils.isNetworkAvailable()){
            listOfNews?.get(position)
                ?.let { navigateToUsersDetailFragment(it.id) }
        }else{
            handleNoConnectionData(position)
        }


    }

    private fun handleNoConnectionData(position: Int) {
        listOfNews?.get(position)
            ?.let { navigateToUsersDetailFragmentWithNoConnection(it.title,it.content,it.image) }
    }

    private fun navigateToUsersDetailFragmentWithNoConnection(
        title: String,
        content: String,
        image: String
    ) {
        val bundle = Bundle().apply {
            putString(BundleKeys.NEW_TITLE, title)
            putString(BundleKeys.NEW_CONTENT, content)
            putString(BundleKeys.NEW_IMAGE, image)
        }
        findNavController().navigate(R.id.action_newsFragment_to_newsDetail, bundle)
    }

    private fun navigateToUsersDetailFragment(newId: Int) {
        val bundle = Bundle().apply {
            putInt(BundleKeys.NEW_ID, newId)
        }
        findNavController().navigate(R.id.action_newsFragment_to_newsDetail, bundle)
    }
}
