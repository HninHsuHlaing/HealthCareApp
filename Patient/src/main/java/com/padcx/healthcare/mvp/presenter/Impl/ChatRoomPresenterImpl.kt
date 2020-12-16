package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.healthcare.mvp.presenter.ChatRoomPresenter
import com.padcx.healthcare.mvp.view.ChatView
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.data.vo.SenderVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import com.padcx.shared.util.DateUtil
import java.util.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
class ChatRoomPresenterImpl: ChatRoomPresenter, AbstractBaseePresenter<ChatView>() {

    private val mHealthCareModel = HealthCareModelImpl
    override fun onUiReadyConstulation(consultationChatId: String, owner: LifecycleOwner) {
        mHealthCareModel.getConsultationChat(consultationChatId,onSuccess = {}, onError = {})

        mHealthCareModel.getConsultationChatFromDB(consultationChatId)
                .observe(owner, Observer {data->
                    data?.let {
                        mView.displayPatientInfo(data)
                    }

                })

        mHealthCareModel.getChatMessage(consultationChatId,onSuccess = {}, onError = {})

        mHealthCareModel.getAllChatMessageFromDB()
                .observe(owner, Observer {data->
                    data?.let {
//                        Log.d("chat message", it[0].toString())
                        mView.displayChatMessageList(data)
                    }

                })
    }

    override fun addTextMessage(message: String, consultationChatId: String,
                                senderId: String, senderPhoto: String,
                                senderName: String, owner: LifecycleOwner) {
        val id = UUID.randomUUID().toString()
        var sendBy = SenderVO(
                photo = senderPhoto,
                name = senderName,
                arrived_time = DateUtil().getCurrentHourMinAMPM()
        )
        var chatMessage = MessageVO(id =id,
                messageText = message,
                messageImage = "",
                sendAt = senderId,
                sendBy= sendBy, type= ""
        )
        mHealthCareModel.sendChatMessage(chatMessage,consultationChatId,onSuccess = {},onError = {})
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }

    override fun onTapSendTextMessage() {

    }

    override fun onTapAttachImage() {

    }

    override fun onTapQuestionTemplate() {

    }

    override fun onTapPrescription() {

    }

    override fun onTapDoctorComment() {

    }
}