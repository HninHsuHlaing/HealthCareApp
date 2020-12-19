package com.padcx.doctor.mvp.presenter

import com.padcx.doctor.delegate.MedicalDelegate
import com.padcx.doctor.delegate.PrescriptionInfoDelegate
import com.padcx.doctor.mvp.view.PrescriptionInfoView
import com.padcx.doctor.mvp.view.PrescriptionView
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/19/2020
 */
interface PrescriptionInfoPresenter : BasePresenter<PrescriptionInfoView>, PrescriptionInfoDelegate {
    fun onUiReadyForPrescription ( consulation_chat_id : String)
}