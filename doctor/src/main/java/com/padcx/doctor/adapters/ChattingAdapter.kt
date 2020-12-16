package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctor.R
import com.padcx.doctor.delegate.ChatRoomDelegate
import com.padcx.doctor.viewHolder.BaseChatViewHolder
import com.padcx.doctor.viewHolder.ConsultationChatDoctorViewHolder
import com.padcx.doctor.viewHolder.ConsultationChatPatientViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.util.PATIENT

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
class ChattingAdapter (delegate : ChatRoomDelegate) : BaseRecyclerAdapter<BaseChatViewHolder, MessageVO>(){

    val mDelegate: ChatRoomDelegate =delegate

    val view_Type_Patient = 1
    val view_Type_Doctor =2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseChatViewHolder {
        when(viewType) {
            view_Type_Patient -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.listitem_chat_patient, parent, false)

                return ConsultationChatPatientViewHolder(view, mDelegate)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.listitem_chat_doctor, parent, false)

                return ConsultationChatDoctorViewHolder(view, mDelegate)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        when{
            mData[position].sendAt == PATIENT ->{
                return view_Type_Patient
            }else->{
            return view_Type_Doctor
        }
        }
    }

}