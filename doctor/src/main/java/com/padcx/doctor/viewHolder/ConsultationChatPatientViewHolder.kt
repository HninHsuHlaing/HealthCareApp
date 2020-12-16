package com.padcx.doctor.viewHolder

import android.view.View
import com.padcx.doctor.R
import com.padcx.doctor.delegate.ChatRoomDelegate
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.listitem_chat_patient.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
class ConsultationChatPatientViewHolder(itemView: View, private val mDelegate: ChatRoomDelegate) :
        BaseChatViewHolder(itemView) {
    override fun bindData(data: MessageVO) {
        data.sendBy?.photo?.let{
            ImageUtil().showImage(itemView.patient_photo, data.sendBy?.photo.toString(), R.drawable.user)
        }
        itemView.patient_timestamp.text = data.sendBy?.arrived_time
        itemView.pateint_text_body.text = data.messageText
    }
}