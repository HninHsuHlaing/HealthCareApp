package com.padcx.shared.adapters

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */

import androidx.recyclerview.widget.RecyclerView
import com.padcx.shared.view.viewholder.BaseViewHolder


abstract class BaseRecyclerAdapter<T : BaseViewHolder<W>,W>
    : RecyclerView.Adapter<T>() {

    var mData : MutableList<W> = arrayListOf()

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    fun setNewData(data : MutableList<W>){
        mData = data
        notifyDataSetChanged()
    }

    fun appendNewData(data : List<W>){
        mData.addAll(data)
        notifyDataSetChanged()
    }
}