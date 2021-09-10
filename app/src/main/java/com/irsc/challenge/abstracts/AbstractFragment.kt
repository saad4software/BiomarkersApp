package com.irsc.challenge.abstracts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.irsc.challenge.R
import com.irsc.challenge.models.SharedViewModel


abstract class AbstractFragment : Fragment() {

    protected val model: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.progress.observe(requireActivity(), Observer<Boolean>{ show ->
            showProgress(show)
        })

        model.message.observe(requireActivity(), Observer<String?>{ msg ->
            toastMessage(msg)
        })

    }

    private fun showProgress(show: Boolean) {

        if (show){
            this.requireView().findNavController().navigate(R.id.progressDialogFragment)
        } else
        {
            this.requireView().findNavController().popBackStack(R.id.progressDialogFragment, true)
        }

    }

    private fun toastMessage(message: String?) {
        message?.let{
            Snackbar.make(this.requireView(),it, Snackbar.LENGTH_LONG).show()
        }
    }

}