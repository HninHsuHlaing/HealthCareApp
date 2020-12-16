package com.padcx.healthcare.views.viewHolders

import android.view.View
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ChatRoomDelegate
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.listitem_chat_patient.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
class ConsultationChatPatientViewHolder(itemView: View, private val mDelegate: ChatRoomDelegate) :
        BaseChatViewHolderr(itemView) {
    override fun bindData(data: MessageVO) {
        data.sendBy?.photo?.let{
            ImageUtil().showImage(itemView.patient_photo, data.sendBy?.photo.toString(), R.drawable.user)
        }

        itemView.ptxt_time_stamp.text = data.sendBy?.arrived_time
        itemView.ptext_message_body.text = data.messageText
    }
}