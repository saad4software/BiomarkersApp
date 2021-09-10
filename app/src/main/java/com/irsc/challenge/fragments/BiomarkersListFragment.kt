package com.irsc.challenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.irsc.challenge.abstracts.AbstractFragment
import com.irsc.challenge.R
import com.irsc.challenge.databinding.FragmentBiomarkersListBinding
import com.irsc.challenge.delegates.AdapterDelegate
import com.irsc.challenge.holders.ItemBiomarkHolder
import com.irsc.challenge.models.MarkModel
import com.irsc.challenge.utils.GenericAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BiomarkersListFragment : AbstractFragment(), AdapterDelegate<ItemBiomarkHolder> {

    private lateinit var binding:FragmentBiomarkersListBinding
    private val genericAdapter = GenericAdapter<MarkModel, ItemBiomarkHolder>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentBiomarkersListBinding>(
            inflater,
            R.layout.fragment_biomarkers_list, container, false
        )
        genericAdapter.setup(binding.rvMain, this)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.refresher.setOnRefreshListener {
            genericAdapter.initiate()
        }
        genericAdapter.initiate()

    }


    override fun adapterOnLastItem(senderId: Int, page: Int) {
        model.getBiomarks().observe(requireActivity()){ list ->
            genericAdapter.noInternet = list == null
            genericAdapter.addAll(list)
            binding.refresher.isRefreshing = false



        }

    }

    override fun adapterOnItemSelect(senderId: Int, index: Int) {
        genericAdapter.getItem(index)?.let { model.select(it) }
        this.requireView().findNavController().navigate(R.id.action_biomarkersListFragment_to_biomarkDetailsFragment)

    }

    override fun adapterOnBindHolder(senderId: Int, index: Int, holder: ItemBiomarkHolder) {
        genericAdapter.getItem(index)?.let { holder.bind(it) }
    }

    override fun adapterOnCreateHolder(senderId: Int, parent: ViewGroup): ItemBiomarkHolder {
        return ItemBiomarkHolder.newInstance(parent)
    }



}
