package com.padcx.healthcare.mvp.presenter

import com.padcx.healthcare.delegate.PrescriptionInfoDelegate
import com.padcx.healthcare.mvp.view.PrescriptionInfoView
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/19/2020
 */
interface PrescriptionInfoPresenter  : BasePresenter<PrescriptionInfoView>, PrescriptionInfoDelegate {
    fun onUiReadyForPrescription ( consulation_chat_id : String)
}