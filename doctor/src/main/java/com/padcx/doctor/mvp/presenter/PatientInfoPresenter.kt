package com.padcx.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.delegate.QuestionAndAnswerDelegate
import com.padcx.doctor.mvp.view.PatientInfoView
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/12/2020
 */
interface PatientInfoPresenter  : BasePresenter<PatientInfoView> , QuestionAndAnswerDelegate {
    fun onTapStartConsultation(  consultationRequestVO: ConsulationRequestVO)
    fun onUiReadyConstulation( consulationRequestId : String ,  owner: LifecycleOwner)
}