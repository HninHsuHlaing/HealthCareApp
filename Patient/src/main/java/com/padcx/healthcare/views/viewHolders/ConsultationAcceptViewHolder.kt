package com.padcx.healthcare.views.viewHolders

import android.view.View
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ConsultationAcceptDelegate
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.util.ImageUtil
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.consultation_request_viewpod.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/11/2020
 */
class ConsultationAcceptViewHolder(itemView: View, private val mDelegate: ConsultationAcceptDelegate) :
    BaseViewHolder<ConsulationRequestVO>(itemView) {
    override fun bindData(data: ConsulationRequestVO) {
        data?.let {

            itemView.txt_consulation.text = data.speciality+ itemView.resources.getString(R.string.consultation_request_message)
            ImageUtil().showImage(itemView.img_userprofile, data.doctor.photo.toString(), R.drawable.doctor_thumbnail)
            itemView.txt_doctorname.text = data.doctor?.name
            itemView.txt_specialityname.text = data.doctor?.speciality
            itemView.txt_doctor_bigoraphy.text = data.doctor?.biography
        }

        itemView.btnstart.setOnClickListener {
            mDelegate.onTapStarted(
                consultationChatId = data.cr_id.toString(), consultationRequestVO = data
            )
        }
    }
}