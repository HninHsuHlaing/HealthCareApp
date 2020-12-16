package com.padcx.healthcare.mvp.view

import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
interface ChatView :BaseView {
    fun displayPatientInfo(consultationChatVO: ConsulationChatVO)
    fun displayChatMessageList(list : List<MessageVO>)
}