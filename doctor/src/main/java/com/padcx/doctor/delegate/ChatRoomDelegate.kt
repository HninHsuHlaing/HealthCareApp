package com.padcx.doctor.delegate

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
interface ChatRoomDelegate {
    fun onTapSendTextMessage(message : String )
    fun onTapAttachImage()
    fun onTapQuestionTemplate()
    fun onTapPrescription()
    fun onTapDoctorComment()
}