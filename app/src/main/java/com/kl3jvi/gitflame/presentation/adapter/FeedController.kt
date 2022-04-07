package com.kl3jvi.gitflame.presentation.adapter

import androidx.navigation.findNavController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.kl3jvi.gitflame.HomeFeedBindingModel_
import com.kl3jvi.gitflame.data.remote.dto.EventModelItem
import com.kl3jvi.gitflame.presentation.ui.home.HomeFragmentDirections

class FeedController : PagingDataEpoxyController<EventModelItem>() {
    override fun buildItemModel(currentPosition: Int, item: EventModelItem?): EpoxyModel<*> {
        return HomeFeedBindingModel_()
            .id(item?.id)
            .clickListener { view ->
                try {
                    item?.let {
                        val directions =
                            HomeFragmentDirections.actionNavigationHomeToDetailsFragment()
                        view.findNavController().navigate(directions)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            .eventItem(item)
    }
}