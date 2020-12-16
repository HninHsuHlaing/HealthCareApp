package com.padcx.healthcare.views.viewHolders

import android.view.View
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ChatHistoryDelegate
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.util.ImageUtil
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.listitem_chathistory.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class ChatHistoryViewHolder (itemView: View, private val mDelegate: ChatHistoryDelegate) :
        BaseViewHolder<ConsulationChatVO>(itemView) {


    override fun bindData(data: ConsulationChatVO) {

        data?.let {
            itemView.startconservationdate.text =data.start_consultation_date
            ImageUtil().showImage(itemView.doctorphoto, data.doctor?.photo.toString(), R.drawable.doctor_thumbnail)
            itemView.chat_doctor_name.text = data.doctor?.name
            itemView.chat_doctor_specialityname.text = data.doctor?.speciality
        }

        itemView.sendtextlayout.setOnClickListener {
            mDelegate.onTapSendMessage(data)
        }

        itemView.prescriptionlayout.setOnClickListener {
            mDelegate.onTapPrescription(data)
        }


    }
}