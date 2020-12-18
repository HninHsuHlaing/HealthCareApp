package com.padcx.doctor.viewHolder

import android.view.View
import com.padcx.doctor.R
import com.padcx.doctor.delegate.MedicalDelegate
import com.padcx.shared.data.vo.FrequentlyMedicineVO
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.listitem_medicine.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class MedicalViewHolder(itemView: View, private val mDelegate: MedicalDelegate) :
        BaseViewHolder<FrequentlyMedicineVO>(itemView) {

    override fun bindData(data: FrequentlyMedicineVO) {

        data?.let {
            itemView.medicine_name.text = data.name
            if(data.isSelected == false)
            {
                itemView.checkbtn.setImageResource(R.drawable.add)
            }else
            {
                itemView.checkbtn.setImageResource(R.drawable.minussign)
            }
        }

        itemView.checkbtn.setOnClickListener {
            if(data.isSelected ==false) {

                data?.let {
                    itemView.checkbtn.setImageResource(R.drawable.minussign)
                    mDelegate.onTapSelectMedicine(data)
                    data.isSelected= true
                }
            }
            else{
                itemView.checkbtn.setImageResource(R.drawable.add)
                data.isSelected =false
                mDelegate.onTapRemoveMedicine(data)
            }

        }
    }
}