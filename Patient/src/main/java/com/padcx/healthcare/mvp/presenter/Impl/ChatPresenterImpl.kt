package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.healthcare.mvp.presenter.ChatPresenter
import com.padcx.healthcare.mvp.view.ChatHistoryView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class ChatPresenterImpl: ChatPresenter, AbstractBaseePresenter<ChatHistoryView>() {

    private val mHealthCareModel : HealthCareModel = HealthCareModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

        mHealthCareModel.getConsultationChatByPatientId(SessionManager.patient_id.toString(),onSuccess = {}, onError = {})

        mHealthCareModel.getConsultationChatByPatientIdFromDB(SessionManager.patient_id.toString())
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayChatHistoryList(data)
                    }
                })

    }

    override fun onTapSendMessage(consultationChatVO: ConsulationChatVO) {
        mView?.nextPageToChatRoom(consultationChatVO.id)
    }

    override fun onTapPrescription(consultationChatVO: ConsulationChatVO) {
        mView?.showPrescriptionDialog(consultationChatVO.status,consultationChatVO.id,
                consultationChatVO.patient?.name.toString(),
                consultationChatVO.start_consultation_date.toString())
    }
}