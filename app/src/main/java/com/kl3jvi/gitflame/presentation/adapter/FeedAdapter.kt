package com.kl3jvi.gitflame.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kl3jvi.gitflame.data.model.EventModelItem
import com.kl3jvi.gitflame.databinding.ItemHomeFeedBinding

class FeedAdapter : ListAdapter<EventModelItem, FeedAdapter.FeedViewHolder>(
    FeedDiffCallback()
) {

    inner class FeedViewHolder constructor(
        private val binding: ItemHomeFeedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
//            binding.setClickListener { view ->
//                binding.pokemonObj?.let { pokemonDetails ->
//                    navigateToDetails(pokemonDetails, view)
//                }
//            }
        }

//        private fun navigateToDetails(pokemon: Pokemon, view: View) {
//            val direction =
//                MainFragmentDirections.actionMainFragmentToDetailsFragment(pokemon)
//            view.findNavController().navigate(direction)
//        }

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

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) =
        holder.bindItem(getItem(position))
}

private class FeedDiffCallback : DiffUtil.ItemCallback<EventModelItem>() {

    override fun areItemsTheSame(
        oldItem: EventModelItem,
        newItem: EventModelItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: EventModelItem,
        newItem: EventModelItem
    ): Boolean {
        return oldItem == newItem
    }
}