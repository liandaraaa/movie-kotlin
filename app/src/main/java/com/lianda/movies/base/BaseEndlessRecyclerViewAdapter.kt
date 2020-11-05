package com.lianda.movies.base

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class BaseEndlessRecyclerViewAdapter<T>(
    open val context: Context,
    open var datas: MutableList<T>
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOAD_MORE = 1
    }

    var page = 1
    var totalPage = 1

    var isLoadMoreLoading = false

    lateinit var onLoadMoreListener: OnLoadMoreListener

    private var scrollListener: RecyclerView.OnScrollListener? = null

    var layoutManager: LinearLayoutManager? = null

    protected fun getView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(context).inflate(getItemResourceLayout(viewType), parent, false)
    }

    protected abstract fun getItemResourceLayout(viewType: Int): Int

    protected abstract fun setLoadMoreProgress(isProgress: Boolean)

    var recyclerView: RecyclerView? = null
        set(recyclerView) {
            scrollListener = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    layoutManager?.let{layoutManager ->
                        val totalItemCount = layoutManager.itemCount
                        val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                        val isLastPosition = totalItemCount.minus(1) == lastVisibleItem
                        Log.d("lotmor", "countItem: $totalItemCount")
                        Log.d("lotmor", "lastVisiblePosition: $lastVisibleItem")
                        Log.d("lotmor", "isLastPosition: $isLastPosition")
                        Log.d("lotmor", "page: $page")
                        Log.d("lotmor", "totalPage: $totalPage")
                        Log.d("lotmor", "isLoadMore: $isLoadMoreLoading")

                        if (!isLoadMoreLoading && isLastPosition && page < totalPage) {
                            onLoadMoreListener.onLoadMore()
                            isLoadMoreLoading = true
                        }
                    }
                }
            }

            recyclerView!!.addOnScrollListener(scrollListener as RecyclerView.OnScrollListener)
            field = recyclerView
            Log.d("LoadMore", "scroll listener set")
        }

    fun removeScrollListener() {
        scrollListener?.let {
            recyclerView?.removeOnScrollListener(it)
            Log.d("LoadMore", "scroll listener removed")
        }
    }

    fun notifyAddOrUpdateChanged(newDatas: List<T>) {
        newDatas.forEach { data ->
            if (!datas.contains(data)) {
                datas.add(data)
            }

            notifyDataSetChanged()
        }
    }

    fun notifyUpdateChanged(newDatas: List<T>) {
        datas.clear()
        datas.addAll(newDatas)
        notifyDataSetChanged()
    }

    fun clear(){
        datas.clear()
        notifyDataSetChanged()
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

}