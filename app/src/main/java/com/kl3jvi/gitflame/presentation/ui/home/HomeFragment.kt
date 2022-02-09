package com.kl3jvi.gitflame.presentation.ui.home

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.network_state.State
import com.kl3jvi.gitflame.common.utils.*
import com.kl3jvi.gitflame.common.utils.Constants.getSafeString
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
    private lateinit var username: String

    override fun onStart() {
        super.onStart()
        handleNetworkChanges()
    }

    override fun observeViewModel() = getUser()

    private fun getUser() {
        collectFlow(loginViewModel.getUser()) { state ->
            when (state) {
                is State.Error -> {
                    showLoading(false)
                    Log.e("Error",state.message)
                    showToast(state.message)
                }
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    username = getSafeString(state.data.login)
                    if (!username.isNullOrEmpty()) {
                        getUserFeed(username)
                        binding.swipeRefreshLayout.setOnRefreshListener { getUserFeed(username) }
                        showLoading(false)
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
        observeLiveData(NetworkUtil.getNetworkLiveData(requireActivity()), this) { isConnected ->
            if (!isConnected) showToast("No Internet Connection!")
            else if (adapter.itemCount == 0 && ::username.isInitialized) getUserFeed(username)
        }
    }

}