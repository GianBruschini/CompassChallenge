package com.conexa.conexachallenge.presentation.user


import android.os.Bundle
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
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.databinding.FragmentUsersBinding
import com.conexa.conexachallenge.presentation.adapters.UsersAdapter
import com.conexa.conexachallenge.presentation.base.BaseFragment
import com.conexa.conexachallenge.util.showCustomToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(
    FragmentUsersBinding::inflate,
),UsersAdapter.OnItemClickListener{
    private var listOfUsers: ArrayList<UserResponse>? = null
    private val viewModel: UsersViewModel by viewModels()
    private var userAdapter: UsersAdapter = UsersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        initOnClicks()
        initRv()
        observeViewModel()
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModel.fetchUsers()
    }

    private fun initOnClicks() {
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }



    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect { state ->
                    handleUserList(state.users)
                    handleLoadingIndicator(state.loading)
                    handleUserMessage(state.userMessage)
                }
            }
        }
    }

    private fun handleUserMessage(userMessage: String?) {
        if(userMessage!=null){
            ContextCompat.getDrawable(requireContext(), R.drawable.rounded_gradient_error)
                ?.let { drawable ->
                    Toast(requireContext()).showCustomToast(
                        userMessage.toString(),
                        drawable,
                        requireActivity()
                    )
                }
        }
    }

    private fun handleUserList(usersList: List<UserResponse>) {
        if (usersList.isEmpty()) {
            binding.noResultsTxt.visibility = View.VISIBLE
        } else {
            binding.noResultsTxt.visibility = View.GONE
            val arrayListOfUsers = ArrayList(usersList)
            fillUsersRv(arrayListOfUsers)
        }
    }


    private fun fillUsersRv(userList: ArrayList<UserResponse>) {
        val arrayListOfUsers = ArrayList(userList)
        userAdapter.setList(arrayListOfUsers)
        userAdapter.notifyDataSetChanged()
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

    private fun initRv() {
        with(binding.usersRv) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = userAdapter
            userAdapter.setOnItemClickListener(this@UsersFragment)
        }
    }

    override fun onitemClick(position: Int) {

    }

}