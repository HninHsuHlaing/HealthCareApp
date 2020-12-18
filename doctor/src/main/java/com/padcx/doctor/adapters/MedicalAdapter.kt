package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctor.R
import com.padcx.doctor.delegate.MedicalDelegate
import com.padcx.doctor.viewHolder.MedicalViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.FrequentlyMedicineVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class MedicalAdapter (private val mDelegate: MedicalDelegate) :
        BaseRecyclerAdapter<MedicalViewHolder, FrequentlyMedicineVO>() {

    fun setMedicineList(list : ArrayList<FrequentlyMedicineVO>)
    {
        mData = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_medicine, parent, false)
        return MedicalViewHolder(view, mDelegate)

    }
}
