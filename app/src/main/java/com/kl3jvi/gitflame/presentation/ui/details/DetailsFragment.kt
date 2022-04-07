package com.kl3jvi.gitflame.presentation.ui.details

import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.databinding.DetailsFragmentBinding
import com.kl3jvi.gitflame.presentation.activities.MainActivity
import com.kl3jvi.gitflame.presentation.base.BindingFragment


class DetailsFragment : BindingFragment<DetailsFragmentBinding>(R.layout.details_fragment) {


    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavBar()
        }
    }

    override fun observeViewModel() {}

    override fun initViews() {}

}