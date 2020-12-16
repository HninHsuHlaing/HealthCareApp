package com.padcx.healthcare.delegate

/**
 * Created by Hnin Hsu Hlaing
 * on 12/11/2020
 */
interface ChatRoomDelegate {
    fun onTapSendTextMessage()
    fun onTapAttachImage()
    fun onTapQuestionTemplate()
    fun onTapPrescription()
    fun onTapDoctorComment()
}