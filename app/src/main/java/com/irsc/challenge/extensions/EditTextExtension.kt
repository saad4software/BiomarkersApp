package com.irsc.challenge.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


fun EditText.afterChanged(callback:(txt:String)->(Unit)) {
    this.addTextChangedListener(object: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            callback(s.toString())
        }

    })
}
