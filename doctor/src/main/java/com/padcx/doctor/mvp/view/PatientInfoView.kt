package com.padcx.doctor.mvp.view

import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/12/2020
 */
interface PatientInfoView: BaseView {
    fun displayPatientInfo(consultationRequestVO: ConsulationRequestVO)
    fun nextPageToChat(consulation_chat_id : String)
}