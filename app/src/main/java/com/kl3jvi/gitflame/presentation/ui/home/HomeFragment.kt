package com.kl3jvi.gitflame.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.databinding.FragmentHomeBinding
import com.kl3jvi.gitflame.presentation.activities.login.LoginViewModel
import com.kl3jvi.gitflame.presentation.adapter.FeedAdapter
import com.kl3jvi.gitflame.presentation.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()

    private val adapter = FeedAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding {
            feedList.adapter = adapter

        }

    }


}