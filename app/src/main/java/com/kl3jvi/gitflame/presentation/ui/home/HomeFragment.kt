package com.kl3jvi.gitflame.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.network_state.State
import com.kl3jvi.gitflame.common.utils.NetworkUtil
import com.kl3jvi.gitflame.common.utils.collectFlow
import com.kl3jvi.gitflame.common.utils.collectLatestFlow
import com.kl3jvi.gitflame.common.utils.showToast
import com.kl3jvi.gitflame.databinding.FragmentHomeBinding
import com.kl3jvi.gitflame.presentation.activities.login.LoginViewModel
import com.kl3jvi.gitflame.presentation.adapter.FeedAdapter
import com.kl3jvi.gitflame.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var adapter: FeedAdapter

    override fun onStart() {
        super.onStart()
        handleNetworkChanges()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        loginViewModel.saveState()
    }


    override fun observeViewModel() {
        getUser()
    }

    private fun getUser() {
        collectFlow(homeViewModel.getToken()) { accessToken ->
            collectFlow(loginViewModel.getUser(accessToken)) { state ->
                when (state) {
                    is State.Error -> {
                        showLoading(false)
                        showToast(state.message)
                    }
                    is State.Loading -> showLoading(true)
                    is State.Success -> {
                        val username = state.data.login
                        if (!username.isNullOrEmpty()) {
                            getUserFeed(username)
                            binding.swipeRefreshLayout.setOnRefreshListener { getUserFeed(username) }
                            showLoading(false)
                        }
                    }
                }
            }
        }
    }

    private fun getUserFeed(username: String) {
        collectLatestFlow(homeViewModel.getUserFeed(username)) {
            adapter.submitData(it)
            showLoading(false)
        }
    }

    override fun initViews() {
        adapter = FeedAdapter()
        binding {
            feedList.layoutManager = LinearLayoutManager(requireContext())
            feedList.adapter = adapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun handleNetworkChanges() {
        NetworkUtil.getNetworkLiveData(requireActivity()).observe(this) { isConnected ->
            if (!isConnected) showToast("No Internet Connection!")
//            else if (adapter.itemCount == 0) getUserFeed(username)
        }
    }

}