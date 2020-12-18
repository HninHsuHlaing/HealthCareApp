package com.padcx.doctor.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.delegate.MedicalRecordDelegate
import com.padcx.doctor.mvp.view.MedicalRecordView
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
interface MedicalRecordPresenter: BasePresenter<MedicalRecordView>, MedicalRecordDelegate {
    fun onTapSaveMedicalRecord(consultationChatVO: ConsulationChatVO, owner: LifecycleOwner)
}