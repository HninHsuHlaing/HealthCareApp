package com.padcx.doctor.delegate

import com.padcx.shared.data.vo.ConsulationChatVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface ConsultationAcceptDelegate {
    fun onTapMedicalRecord(consultationRequestVO: ConsulationChatVO)
    fun onTapPrescription(consultationRequestVO: ConsulationChatVO)
    fun onTapSendMessage(consultationRequestVO: ConsulationChatVO)
    fun onTapDoctorComment(consultationRequestVO: ConsulationChatVO)
}