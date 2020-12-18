package com.padcx.doctor.mvp.presenter

import com.padcx.doctor.delegate.MedicalDelegate
import com.padcx.doctor.mvp.view.PrescriptionView
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface PrescriptionPresenter : BasePresenter<PrescriptionView>, MedicalDelegate {
    fun onUiReadyForPrescription ( speciality : String)
    fun onTapFinishConsulation(list : List<PrescriptionVO>, consultationChatVO: ConsulationChatVO)
}