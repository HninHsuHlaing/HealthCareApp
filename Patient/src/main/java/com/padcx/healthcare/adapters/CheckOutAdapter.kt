package com.padcx.healthcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.CheckoutDelegate
import com.padcx.healthcare.views.viewHolders.MedicineListViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.PrescriptionVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class CheckOutAdapter (private val mDelegate: CheckoutDelegate) :
        BaseRecyclerAdapter<MedicineListViewHolder, PrescriptionVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineListViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.medicine_listitem, parent, false)
        return MedicineListViewHolder(view, mDelegate)

    }
}