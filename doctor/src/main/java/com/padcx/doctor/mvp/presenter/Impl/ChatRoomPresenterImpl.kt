package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.delegate.ChatRoomDelegate
import com.padcx.doctor.delegate.QuestionAndAnswerDelegate
import com.padcx.doctor.mvp.presenter.ChatRoomPresenter
import com.padcx.doctor.mvp.view.ChatView
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.data.vo.SenderVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import com.padcx.shared.mvp.presenter.BasePresenter
import com.padcx.shared.util.DOCTOR
import com.padcx.shared.util.DateUtil
import java.util.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
class ChatRoomPresenterImpl : ChatRoomPresenter, AbstractBaseePresenter<ChatView>() {

    private val mHealthCareModel = HealthCareModelImpl

    override fun onUiReadyConstulation(consultationChatId: String, owner: LifecycleOwner) {

        mHealthCareModel.getConsultationChat(consultationChatId,onSuccess = {}, onError = {})

        mHealthCareModel.getConsultationChatFromDB(consultationChatId)
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayPatientInfo(data)
                    }
                })


        mHealthCareModel.getChatMessage(consultationChatId, onSuccess = {}, onError = {})
        mHealthCareModel.getAllChatMessageFromDB()
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayChatMessageList(data)
                    }
                })
        mHealthCareModel.getPrescription(consultationChatId, onSuccess = {}, onError = {})

        mHealthCareModel.getPrescriptionFromDB()
                .observe(owner, Observer {
                    it?.let{
                        mView?.displayPrescriptionViewPod(it)
                    }
                })
    }

    override fun addTextMessage(message: String, consultationChatId: String, senderId: String, senderPhoto: String,
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
                sendAt = DateUtil().getCurrentHourMinAMPM(),
                sendBy= sendBy,
                type= DOCTOR
        )
//        var chatMessage = MessageVO(id =id, message, "", DateUtil().getCurrentDateTime(), sendBy, type
//        )
        mHealthCareModel.sendChatMessage(chatMessage,consultationChatId,onSuccess = {} , onError = {})
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }

    override fun onTapSendTextMessage(message: String) {

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