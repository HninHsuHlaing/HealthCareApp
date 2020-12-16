package com.padcx.healthcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.RecentlyDoctorViewItemDelegate
import com.padcx.healthcare.views.viewHolders.RecentlyDoctorViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.RecentlyDoctorVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class RecentDoctorAdapter(private val mDelegate: RecentlyDoctorViewItemDelegate) :
    BaseRecyclerAdapter<RecentlyDoctorViewHolder, RecentlyDoctorVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyDoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recent_doctor_item, parent, false)
        return RecentlyDoctorViewHolder(view, mDelegate)
    }
}