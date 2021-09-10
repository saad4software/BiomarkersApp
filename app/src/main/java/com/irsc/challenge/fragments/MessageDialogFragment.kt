package com.irsc.challenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.irsc.challenge.R
import com.irsc.challenge.databinding.FragmentMessageDialogBinding
import com.irsc.challenge.databinding.FragmentProgressDialogBinding
import com.irsc.challenge.models.MarkModel
import com.irsc.challenge.models.SharedViewModel


class MessageDialogFragment : DialogFragment() {

    private lateinit var binding:FragmentMessageDialogBinding
    val model: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentMessageDialogBinding>(inflater,
            R.layout.fragment_message_dialog,container,false)

        model.selected.observe(viewLifecycleOwner, Observer<MarkModel> { item ->
            binding.mark = item
        })

        return binding.root
    }

}