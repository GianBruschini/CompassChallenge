package com.compass.compasschallenge.presentation.content


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.compass.compasschallenge.databinding.FragmentContentBinding


import com.compass.compasschallenge.presentation.adapters.SimpleStringAdapter


import com.compass.compasschallenge.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WebPageContentFragment : BaseFragment<FragmentContentBinding>(
    FragmentContentBinding::inflate,
) {
    private val viewModel: WebPageContentViewModel by viewModels()
    private lateinit var every10thCharAdapter: SimpleStringAdapter
    private lateinit var wordCountAdapter: SimpleStringAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeViewModel()
    }

    private fun initUi() {
        every10thCharAdapter = SimpleStringAdapter()
        wordCountAdapter = SimpleStringAdapter()

        binding.every10thCharRecyclerView.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = every10thCharAdapter
        }

        binding.wordCountRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = wordCountAdapter
        }

        binding.fetchButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            fetchContent()
        }
    }

    private fun fetchContent() {
        viewModel.fetchContent()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { state ->
                    binding.progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE
                    every10thCharAdapter.submitList(state.every10thCharacter.split("\n"))
                    wordCountAdapter.submitList(state.wordCount.split("\n"))
                    if (state.errorMessage != null) {
                        Toast.makeText(context, state.errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}

