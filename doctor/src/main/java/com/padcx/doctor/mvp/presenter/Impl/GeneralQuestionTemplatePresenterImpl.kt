package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenter.GeneralQuestionTemplatePresenter
import com.padcx.doctor.mvp.view.GeneralQuestionTemplateView
import com.padcx.doctor.util.SessionManager
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.GeneralQuestionTemplateVO
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.data.vo.SenderVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import com.padcx.shared.util.DOCTOR
import com.padcx.shared.util.DateUtil

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class GeneralQuestionTemplatePresenterImpl : GeneralQuestionTemplatePresenter, AbstractBaseePresenter<GeneralQuestionTemplateView>() {

    private val mHealthCareModel : HealthCareModel = HealthCareModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mHealthCareModel.getGeneralQuestionFromNetwork(onSuccess = {}, onError = {})
        mHealthCareModel.getGeneralQuestionFromDB()
                .observe(owner, Observer {
                    mView?.displayGeneralQuestions(it)
                })
    }

    override fun onTapOneQuestion(generalQuestionTemplateVO: GeneralQuestionTemplateVO) {
        mView?.navigateToToChatRoom(generalQuestionTemplateVO.question.toString())
//        val  senderVO = SenderVO(
//                id = generalQuestionTemplateVO.id,
//                arrived_time = DateUtil().getCurrentDateTime(),
//                name = SessionManager.doctor_name
//        )
//        val messageVo = MessageVO(
//                id = generalQuestionTemplateVO.id,
//                sendAt = DOCTOR,
//                sendBy = senderVO,
//                messageImage = "",
//                messageText = generalQuestionTemplateVO.question
//        )
//        mHealthCareModel.sendChatMessage(messageVo,)
       // mHealthCareModel.sendChatMessage(generalQuestionTemplateVO,)
    }
}