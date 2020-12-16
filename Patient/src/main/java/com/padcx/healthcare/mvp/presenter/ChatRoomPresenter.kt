package com.padcx.healthcare.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padcx.healthcare.delegate.ChatRoomDelegate
import com.padcx.healthcare.delegate.QuestionAndAnswerDelegate
import com.padcx.healthcare.mvp.view.ChatView
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
interface ChatRoomPresenter: BasePresenter<ChatView>, ChatRoomDelegate, QuestionAndAnswerDelegate {
    fun onUiReadyConstulation( consultationChatId : String ,  owner: LifecycleOwner)
    fun addTextMessage(message : String, consultationChatId: String ,
                       senderId : String ,senderPhoto: String,
                       senderName : String, owner: LifecycleOwner)

}