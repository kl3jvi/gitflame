package com.kl3jvi.gitflame.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.network_state.State
import com.kl3jvi.gitflame.databinding.FragmentHomeBinding
import com.kl3jvi.gitflame.presentation.activities.login.LoginViewModel
import com.kl3jvi.gitflame.presentation.adapter.FeedAdapter
import com.kl3jvi.gitflame.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()

    private lateinit var adapter: FeedAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FeedAdapter()
        binding.feedList.layoutManager = LinearLayoutManager(requireContext())
        binding.feedList.adapter = adapter

    }

    override fun observeViewModel() {
        getUser()
    }

    private fun getUser() {
        lifecycleScope.launch {
            loginViewModel.getToken().collect { collectedToken ->
                loginViewModel.getUser(collectedToken).collect { state ->
                    when (state) {
                        is State.Error -> {
                            showLoading(false)
                        }
                        is State.Loading -> {
                            showLoading(true)
                        }
                        is State.Success -> {
                            val username = state.data.login ?: ""
                            getUserFeed(username)
                            binding.swipeRefreshLayout.setOnRefreshListener { getUserFeed(username) }
                            if (adapter.itemCount == 0) getUserFeed(username)
                            showLoading(false)
                        }
                    }
                }
            }
        }
    }

    private fun getUserFeed(username: String) {
        lifecycleScope.launch {
            homeViewModel.getUserFeed(username).collect {
                adapter.submitData(it)
                showLoading(false)
            }
        }
    }

    override fun initViews() {
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

}