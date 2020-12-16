package com.padcx.healthcare.mvp.view

import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface ChatHistoryView : BaseView{
    fun displayChatHistoryList( list: List<ConsulationChatVO>)
    fun nextPageToChatRoom(consulationchatId : String)
    fun showPrescriptionDialog(finish_consulation:Boolean ,consulationchatId : String,
                               patient_name: String,start_conservation_date : String)
}