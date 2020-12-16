package com.padcx.healthcare.mvp.view

import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.RecentlyDoctorVO
import com.padcx.shared.data.vo.SpecialitiesVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/5/2020
 */
interface HomeView :BaseView {
    fun displayConsultationRequest(consultationRequestVO: List<ConsulationRequestVO>)
    fun displayRecentDoctorList (list : List<RecentlyDoctorVO>)
    fun displaySpecialityList(list: List<SpecialitiesVO>)
    fun nextPageToCaseSummary (specialitiesVO: SpecialitiesVO)
    fun nextPageToChatRoom(consulation_chat_id : String,consultationRequestVO: ConsulationRequestVO)
}