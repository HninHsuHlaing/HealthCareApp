package com.padcx.doctor.delegate

import com.padcx.shared.data.vo.ConsulationRequestVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface ConsultationRequestDelegate {
    fun onTapNext(consultationRequestVO: ConsulationRequestVO)
    fun onTapSkip(consultationRequestVO: ConsulationRequestVO)
    fun onTapPostpone(consultationRequestVO: ConsulationRequestVO)
    fun onTapAccept(consultationRequestVO: ConsulationRequestVO)
    fun onTapPostponeTime(postPoneTime : String, consultationRequestVO: ConsulationRequestVO)
}