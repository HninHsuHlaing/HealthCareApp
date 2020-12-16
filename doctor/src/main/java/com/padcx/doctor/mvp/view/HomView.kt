package com.padcx.doctor.mvp.view

import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface HomView : BaseView{
    fun displayConsultationRequests(list: List<ConsulationRequestVO>)
    fun displayConsultationAcceptList(list: List<ConsulationChatVO>)
    fun displayConsultedPatient(list : List<ConsultatedPatientVO>)
  //  fun displayConsultedPatient(list : List<ConsultedPatientVO>)
  //  fun nextPage(data : ConsulationRequestVO)

    fun nextPageChatRoom(consultation_chat_id : String)
    fun nextPagePatientInfo(consultation_request_id : String)
    fun displayPostPoneChooserDialog(consultationRequestVO: ConsulationRequestVO)
}