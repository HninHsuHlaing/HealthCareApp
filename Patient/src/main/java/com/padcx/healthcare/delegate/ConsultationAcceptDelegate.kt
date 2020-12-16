package com.padcx.healthcare.delegate

import com.padcx.shared.data.vo.ConsulationRequestVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/11/2020
 */
interface ConsultationAcceptDelegate {
    fun onTapStarted(consultationChatId : String, consultationRequestVO: ConsulationRequestVO)
}