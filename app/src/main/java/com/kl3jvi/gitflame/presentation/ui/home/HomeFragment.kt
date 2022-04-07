package com.kl3jvi.gitflame.presentation.ui.home

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.utils.NetworkUtil
import com.kl3jvi.gitflame.common.utils.collectFlow
import com.kl3jvi.gitflame.common.utils.observeLiveData
import com.kl3jvi.gitflame.common.utils.showToast
import com.kl3jvi.gitflame.databinding.FragmentHomeBinding
import com.kl3jvi.gitflame.presentation.activities.MainActivity
import com.kl3jvi.gitflame.presentation.activities.login.LoginViewModel
import com.kl3jvi.gitflame.presentation.adapter.FeedController
import com.kl3jvi.gitflame.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val controller = FeedController()

    override fun onStart() {
        super.onStart()
        handleNetworkChanges()
    }

    override fun observeViewModel() = getUser()

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.showBottomNavBar()
        }
    }

    private fun getUser() {
        getUserFeed()
        binding.swipeRefreshLayout.setOnRefreshListener { getUserFeed() }
        showLoading(false)
    }


    private fun getUserFeed() {
        collectFlow(homeViewModel.usersFeed) {
            controller.submitData(it)
        }
    }

    override fun initViews() {
        binding.feedList.setController(controller)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun handleNetworkChanges() {
        observeLiveData(
            NetworkUtil.getNetworkLiveData(requireActivity()),
            viewLifecycleOwner
        ) { isConnected ->
            if (!isConnected) showToast("No Internet Connection!")
//            else if (adapter.spanCount == 0 && ::username.isInitialized) getUserFeed(username)
        }
    }
}
