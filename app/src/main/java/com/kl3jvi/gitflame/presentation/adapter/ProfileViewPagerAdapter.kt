package com.kl3jvi.gitflame.presentation.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kl3jvi.gitflame.presentation.ui.profile.profile_tabs.*


private const val NUM_TABS = 6


class ProfileViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return AboutFragment()
            1 -> return OverviewFragment()
            2 -> return RepositoriesFragment()
            3 -> return ActivityFragment()
            4 -> return StarredFragment()
        }
        return GistsFragment()
    }
}