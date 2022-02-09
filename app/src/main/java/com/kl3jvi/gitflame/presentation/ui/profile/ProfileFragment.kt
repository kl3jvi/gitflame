package com.kl3jvi.gitflame.presentation.ui.profile

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.network_state.State
import com.kl3jvi.gitflame.common.utils.collectFlow
import com.kl3jvi.gitflame.databinding.FragmentProfileBinding
import com.kl3jvi.gitflame.presentation.adapter.ProfileViewPagerAdapter
import com.kl3jvi.gitflame.presentation.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun observeViewModel() {
        collectFlow(profileViewModel.getUser()) { state ->
            when (state) {
                is State.Error -> {}
                is State.Loading -> {}
                is State.Success -> {
                    binding.userData = state.data
                }
            }

        }
    }

    override fun initViews() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = ProfileViewPagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter
        val tabTitles = resources.getStringArray(R.array.profile_tabs)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

}