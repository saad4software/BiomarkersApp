package com.irsc.challenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.irsc.challenge.abstracts.AbstractFragment
import com.irsc.challenge.R
import com.irsc.challenge.databinding.FragmentBiomarkDetailsBinding
import com.irsc.challenge.models.MarkModel


class BiomarkerDetailsFragment : AbstractFragment() {

    private lateinit var binding: FragmentBiomarkDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentBiomarkDetailsBinding>(inflater,
            R.layout.fragment_biomark_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.selected.observe(viewLifecycleOwner, Observer<MarkModel> { item ->
            binding.mark = item
        })

        binding.blob.setBlobText("love")
        binding.imgInfo.setOnClickListener{

            it.findNavController().navigate(R.id.messageDialogFragment)
        }
    }


}