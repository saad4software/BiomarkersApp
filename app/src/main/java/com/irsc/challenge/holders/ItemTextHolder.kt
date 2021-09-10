package com.irsc.challenge.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irsc.challenge.databinding.ItemTextBinding
import com.irsc.challenge.models.MarkModel

class ItemTextHolder(var binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(text:MarkModel){
        binding.txt = text.value
    }

    companion object {
        fun newInstance(parent: ViewGroup): ItemTextHolder {
            val itemBinding = ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemTextHolder(itemBinding)
        }
    }

}
