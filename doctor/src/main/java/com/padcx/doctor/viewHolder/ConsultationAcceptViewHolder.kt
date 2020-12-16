package com.padcx.doctor.viewHolder

import android.view.View
import com.padcx.doctor.R
import com.padcx.doctor.delegate.ConsultationAcceptDelegate
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.util.ImageUtil
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.consultation_accept_item.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/10/2020
 */
class ConsultationAcceptViewHolder(itemView: View, private val mDelegate: ConsultationAcceptDelegate) :
    BaseViewHolder<ConsulationChatVO>(itemView) {

    override fun bindData(data: ConsulationChatVO) {
        data?.let {
            ImageUtil().showImage(itemView.img_patient,data.patient?.photo.toString(), R.drawable.user)
            itemView.txt_patient_name.text = data.patient?.name
            itemView.txt_patient_dateofbirth.text = data.patient?.dob

            itemView.txt_consulated_history.setOnClickListener {
            mDelegate.onTapMedicalRecord(data)
            }

            itemView.txt_prescription.setOnClickListener {
                mDelegate.onTapPrescription(data)
            }

            itemView.txt_comment.setOnClickListener {
                mDelegate.onTapDoctorComment(data)
            }
        }
    }
}