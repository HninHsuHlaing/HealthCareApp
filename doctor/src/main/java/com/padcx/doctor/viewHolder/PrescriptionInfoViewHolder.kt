package com.padcx.doctor.viewHolder

import android.view.View
import com.padcx.doctor.delegate.PrescriptionInfoDelegate
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.listitem_prescription_info.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/19/2020
 */
class PrescriptionInfoViewHolder(itemView: View, private val mDelegate: PrescriptionInfoDelegate) :
        BaseViewHolder<PrescriptionVO>(itemView) {

    override fun bindData(data: PrescriptionVO) {

        data?.let {
            itemView.medicine_name.text = data.medicine_name
            itemView.txt_amount.text  = data.routineVO.amount +" mg"
            itemView.txt_quality.text = data.routineVO.quantity + " Tablet"
            itemView.txt_time.text = data.routineVO.days
            var times = data.routineVO.times.toString()
            itemView.txt_count.text = times
            itemView.txt_repeat.text  = data.routineVO.repeat
            itemView.txt_comment.text = data.routineVO.comment
        }

    }
}