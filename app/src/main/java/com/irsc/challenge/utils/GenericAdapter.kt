package com.irsc.challenge.utils

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.core.widget.NestedScrollView
import com.irsc.challenge.holders.ItemNoResultsHolder
import android.view.View
import com.irsc.challenge.delegates.AdapterDelegate
import com.irsc.challenge.holders.ItemLoadMoreHolder


class GenericAdapter<M, H: ViewHolder>: RecyclerView.Adapter<ViewHolder>() {

    private var list =  ArrayList<M>()
    private var originalList =  ArrayList<M>()
    private var context: Context? = null
    private var delegate: AdapterDelegate<H>? = null
    private var page = 1
//    private val selectedItemPosition = -1
//    private val selectedItems: List<Int> = ArrayList()
    private var isDone = false
    private var recyclerView: RecyclerView? = null
    private var nestedScrollView: NestedScrollView? = null

    private var condition :(M)->Boolean = {true}

    var viewId = 0
    var showNoResults = true
    var autoLoad = false
    var noInternet = false

    private var isEmpty = {list.isEmpty()}


    override fun getItemViewType(position: Int): Int {
        // 1 empty, 0 item, 2 load more

        var type = 0
        if (isEmpty()) type = 1 else if (position == list.size) type = 2

        return type

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType == 0)
            return delegate!!.adapterOnCreateHolder(recyclerView!!.id, parent)
        else if(viewType == 1)
            return ItemNoResultsHolder.newInstance(parent)
        else
            return ItemLoadMoreHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isEmpty()) {
            val h = holder as ItemNoResultsHolder
            h.bind(noInternet)
            return
        }
        else if (position == list.size) {
            val h: ItemLoadMoreHolder = holder as ItemLoadMoreHolder
            h.binding.btnLoadMore.visibility = if (isDone) View.GONE else View.VISIBLE
            h.binding.btnLoadMore.setOnClickListener{
                    delegate!!.adapterOnLastItem(recyclerView!!.id, page - 1)
                }
            return
        }
        else {
            holder.itemView.setOnClickListener { v ->
                delegate!!.adapterOnItemSelect(recyclerView!!.id, position)
            }
            context = holder.itemView.context
            delegate!!.adapterOnBindHolder(recyclerView!!.id, position, holder as H)
            if (position == list.size - 1 && autoLoad) {
                if (nestedScrollView != null) {
                    nestedScrollView?.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
                        if (v.getChildAt(v.childCount - 1) != null) {
                            if (scrollY >= v.getChildAt(v.childCount - 1)
                                    .measuredHeight - v.measuredHeight &&
                                scrollY > oldScrollY
                            ) {
                                if (!isDone) delegate?.adapterOnLastItem(recyclerView!!.id, page - 1)
                            }
                        }
                    }
                } else {
                    if (!isDone) delegate?.adapterOnLastItem(recyclerView!!.id, page - 1)
                }
            }
        }
    }

    override fun getItemCount():Int {
        var count = list.size

        if (isEmpty() && isDone && showNoResults) {
            count = 1
        }

//        else if (!isEmpty && !isDone && !autoLoad) {
//            count = list.size + 1
//        }

        return count

    }

    fun addAll(list: List<M>?) {
        isDone = true
        addPage(list)
    }

    fun addPage(list: List<M>?) {
        if (list != null && list.isNotEmpty() && !originalList.containsAll(list)) {
            this.list.addAll(list)
            originalList.addAll(list)
            page += 1
            isDone = isDone || list.size < 10
            filter(condition)
        } else {
            isDone = true
            notifyDataSetChanged()
        }
    }

    fun filter(condition: (M)->Boolean) {
        this.condition = condition
        list = originalList.filter { item-> condition(item) } as ArrayList<M>
        notifyDataSetChanged()
    }

    fun getItem(index: Int): M? {
        return if (index >= 0 && index < list.size) list[index] else null
    }

    fun add(item: M) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun reset() {
        list.clear()
        originalList.clear()
        page = 1
        isDone = false
        notifyDataSetChanged()

    }

    fun initiate(list: List<M>) {
        initiate()
        isDone = true
        addPage(list)
    }


    fun initiate() {
        reset()
        if (recyclerView != null) delegate!!.adapterOnLastItem(recyclerView!!.id, page - 1)
    }

    fun setup(recyclerView: RecyclerView, delegate: AdapterDelegate<H>) {
        this.recyclerView = recyclerView
        viewId = recyclerView.id
        this.recyclerView!!.adapter = this
        this.delegate = delegate
        notifyDataSetChanged()

    }

    fun setup(
        recyclerView: RecyclerView,
        nestedScrollView: NestedScrollView?,
        delegate: AdapterDelegate<H>
    ) {
        this.nestedScrollView = nestedScrollView
        setup(recyclerView, delegate)
    }

    fun done() {
        isDone = true
    }


//    protected void loadImage(String url, ImageView imageView)
//    {
//        if(!isNullOrEmpty(url))
//        {
//            Glide.with(context)
//                    .load(url)
//                    .placeholder(R.drawable.default_image)
//                    .fitCenter()
//                    .into(imageView);
//        }
//    }

    //    protected void loadImage(String url, ImageView imageView)
    //    {
    //        if(!isNullOrEmpty(url))
    //        {
    //            Glide.with(context)
    //                    .load(url)
    //                    .placeholder(R.drawable.default_image)
    //                    .fitCenter()
    //                    .into(imageView);
    //        }
    //    }
    fun getList(): List<M> {
        return list
    }


}

