package com.padcx.healthcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.SpecilitiesViewItemDelegate
import com.padcx.healthcare.views.viewHolders.SpecilitiesViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.SpecialitiesVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class SpecilitiesAdapter(private val mDelegate: SpecilitiesViewItemDelegate) :
    BaseRecyclerAdapter<SpecilitiesViewHolder, SpecialitiesVO>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecilitiesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.specilities_item, parent, false)
        return SpecilitiesViewHolder(view, mDelegate)
    }
}