package com.kl3jvi.gitflame.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.Constants.emptyString
import com.kl3jvi.gitflame.common.State
import com.kl3jvi.gitflame.databinding.FragmentHomeBinding
import com.kl3jvi.gitflame.presentation.activities.login.LoginViewModel
import com.kl3jvi.gitflame.presentation.adapter.FeedAdapter
import com.kl3jvi.gitflame.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

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
                        is State.Error -> {}
                        is State.Loading -> {}
                        is State.Success -> {
                            val username = state.data.login ?: ""
                            getUserFeed(username)
                        }
                    }
                }
            }
        }
    }

    private fun getUserFeed(username: String) {
        lifecycleScope.launch() {
            homeViewModel.getUserFeed(username).collect { state ->
                when (state) {
                    is State.Error -> {
                    }
                    is State.Loading -> {

                    }
                    is State.Success -> {
                        adapter.submitList(state.data)
                    }
                }
            }
        }
    }

    private fun getToken(): String {
        var token = emptyString()
        lifecycleScope.launch {
            loginViewModel.getToken().collect { collectedToken ->
                token = collectedToken
            }
        }
        return token
    }

    override fun initViews() {

    }


}