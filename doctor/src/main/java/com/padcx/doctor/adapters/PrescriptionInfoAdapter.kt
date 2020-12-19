package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctor.R
import com.padcx.doctor.delegate.PrescriptionInfoDelegate
import com.padcx.doctor.viewHolder.PrescriptionInfoViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.PrescriptionVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/19/2020
 */
class PrescriptionInfoAdapter (private val mDelegate: PrescriptionInfoDelegate) :
        BaseRecyclerAdapter<PrescriptionInfoViewHolder, PrescriptionVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionInfoViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_prescription_info, parent, false)
        return PrescriptionInfoViewHolder(view, mDelegate)

    }
}