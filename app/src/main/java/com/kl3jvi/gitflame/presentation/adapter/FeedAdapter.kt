package com.kl3jvi.gitflame.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.gitflame.data.model.EventModelItem
import com.kl3jvi.gitflame.databinding.ItemHomeFeedBinding

class FeedAdapter :
    PagingDataAdapter<EventModelItem, FeedAdapter.FeedViewHolder>(FeedDiffCallback()) {

    inner class FeedViewHolder constructor(
        private val binding: ItemHomeFeedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(event: EventModelItem) {
            binding.apply {
                eventItem = event
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding: ItemHomeFeedBinding = ItemHomeFeedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val event = getItem(position)
        if (event != null) {
            holder.bindItem(event)
        }
    }

}

private class FeedDiffCallback : DiffUtil.ItemCallback<EventModelItem>() {
    override fun areItemsTheSame(oldItem: EventModelItem, newItem: EventModelItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventModelItem, newItem: EventModelItem): Boolean {
        return oldItem == newItem
    }
}