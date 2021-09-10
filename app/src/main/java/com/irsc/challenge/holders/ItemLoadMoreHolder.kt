package com.irsc.challenge.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irsc.challenge.databinding.ItemLoadMoreBinding
import com.irsc.challenge.databinding.ItemNoResultsBinding

class ItemLoadMoreHolder(var binding: ItemLoadMoreBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup): ItemLoadMoreHolder {
            val itemBinding = ItemLoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemLoadMoreHolder(itemBinding)
        }
    }

}
