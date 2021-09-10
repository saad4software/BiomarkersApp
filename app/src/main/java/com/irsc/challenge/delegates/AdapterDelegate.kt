package com.irsc.challenge.delegates

import android.view.ViewGroup

interface AdapterDelegate<T> {
        fun adapterOnLastItem(senderId: Int, page: Int)
        fun adapterOnItemSelect(senderId: Int, index: Int)
        fun adapterOnBindHolder(senderId: Int, index: Int, holder: T)
        fun adapterOnCreateHolder(senderId: Int, parent: ViewGroup): T
}
