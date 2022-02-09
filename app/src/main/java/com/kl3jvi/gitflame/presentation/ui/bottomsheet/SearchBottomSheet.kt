package com.kl3jvi.gitflame.presentation.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kl3jvi.gitflame.databinding.SearchBottomSheetBinding

class SearchBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: SearchBottomSheetBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchBottomSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.let {
            val sheet = it as BottomSheetDialog
            sheet.behavior.state = STATE_EXPANDED
        }

    }


    companion object {
        const val TAG = "SEARCH BOTTOM SHEET"
    }
}