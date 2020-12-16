package com.padcx.healthcare.views.viewHolders

import android.view.View
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ChatRoomDelegate
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.listitem_chat_doctor.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
class ConsultationChatDoctorViewHolder (itemView: View, private val mDelegate: ChatRoomDelegate) :
        BaseChatViewHolderr(itemView) {
    override fun bindData(data: MessageVO) {
        data.sendBy?.photo?.let{
            ImageUtil().showImage(itemView.doctor_photo, data.sendBy?.photo.toString(), R.drawable.user)
        }
        itemView.text_timestamp.text = data.sendBy?.arrived_time
        itemView.text_message_body.text = data.messageText
    }
}