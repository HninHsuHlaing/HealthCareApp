package com.padcx.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.delegate.ChatRoomDelegate
import com.padcx.doctor.delegate.QuestionAndAnswerDelegate
import com.padcx.doctor.mvp.view.ChatView
import com.padcx.doctor.viewPod.PrescriptionViewPod
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
interface ChatRoomPresenter: BasePresenter<ChatView>, ChatRoomDelegate, QuestionAndAnswerDelegate, PrescriptionViewPod.Delegate {
    fun onUiReadyConstulation( consultationChatId : String ,  owner: LifecycleOwner)
    fun addTextMessage(message : String, consultationChatId: String ,
                       senderId : String ,senderPhoto: String,
                       senderName : String, owner: LifecycleOwner)
}