package com.irsc.challenge.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irsc.challenge.delegates.ApiCallsDelegate
import com.irsc.challenge.repository.ApiCalls
import com.irsc.challenge.repository.CallbackWrapped
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject
constructor(
    private val apiCalls: ApiCalls,
) : ViewModel(), ApiCallsDelegate {

    init {
        apiCalls.delegate = this
    }

    val selected = MutableLiveData<MarkModel>()
    val message = MutableLiveData<String?>()
    val progress = MutableLiveData<Boolean>(false)

    private val biomarks: MutableLiveData<List<MarkModel>> by lazy {
        MutableLiveData<List<MarkModel>>().also {
            loadBiomarks()
        }
    }

    fun getBiomarks(): LiveData<List<MarkModel>> {
        if (biomarks.value == null) loadBiomarks()
        return biomarks
    }

    private fun loadBiomarks() {
        apiCalls.getBioMarks(object : CallbackWrapped<List<MarkModel>> {
            override fun onResponse(response: List<MarkModel>) {
                biomarks.value = (response.filter { item -> item.value != null })
                message.value = null
            }

            override fun onFailure(errorMessage: String?) {
                biomarks.value = null
            }

        })
    }


    fun select(item: MarkModel) {
        selected.value = item
    }

    override fun showProgress(show: Boolean) {
        progress.value = show
    }

    override fun toastMessage(message: String?) {
        this.message.value = message
    }

}
