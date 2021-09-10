package com.irsc.challenge.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irsc.challenge.databinding.ItemBiomarkBinding
import com.irsc.challenge.databinding.ItemTextBinding
import com.irsc.challenge.models.MarkModel

class ItemBiomarkHolder(var binding: ItemBiomarkBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MarkModel){
        binding.mark = item
    }

    companion object {
        fun newInstance(parent: ViewGroup): ItemBiomarkHolder {
            val itemBinding = ItemBiomarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemBiomarkHolder(itemBinding)
        }
    }

}
