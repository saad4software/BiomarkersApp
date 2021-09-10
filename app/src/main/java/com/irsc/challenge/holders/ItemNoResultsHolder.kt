package com.irsc.challenge.holders

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.irsc.challenge.databinding.ItemNoResultsBinding

class ItemNoResultsHolder(var binding: ItemNoResultsBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(noInternet: Boolean){
        binding.animationView.setAnimation(if (noInternet) "no_connection.json" else "empty.json")
        binding.animationView.playAnimation()
        binding.tvMain.text = if (noInternet) "No Internet Connection" else "No Data to Show"


    }

    companion object {
        fun newInstance(parent: ViewGroup): ItemNoResultsHolder {
            val itemBinding = ItemNoResultsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemNoResultsHolder(itemBinding)
        }
    }

}
