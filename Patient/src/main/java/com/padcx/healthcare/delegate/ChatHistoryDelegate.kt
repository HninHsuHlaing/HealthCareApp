package com.padcx.healthcare.delegate

import com.padcx.shared.data.vo.ConsulationChatVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface ChatHistoryDelegate {
    fun onTapSendMessage(consultationChatVO: ConsulationChatVO)
    fun onTapPrescription(consultationChatVO: ConsulationChatVO)
}