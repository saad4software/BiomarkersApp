package com.irsc.challenge.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.irsc.challenge.R
import com.irsc.challenge.databinding.FragmentProgressDialogBinding


class ProgressDialogFragment : DialogFragment() {

    private lateinit var binding:FragmentProgressDialogBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentProgressDialogBinding>(inflater,
            R.layout.fragment_progress_dialog,container,false)

        dialog?.setCancelable(false)

        binding.animationView.setAnimation("loading.json")
        binding.animationView.playAnimation()



        return binding.root
    }

}