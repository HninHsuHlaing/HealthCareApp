package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.mvp.presenter.MedicalRecordPresenter
import com.padcx.doctor.mvp.view.MedicalRecordView
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class MedicalRecordPresenterImpl  : MedicalRecordPresenter, AbstractBaseePresenter<MedicalRecordView>() {

    private val mHealthCareModel : HealthCareModel = HealthCareModelImpl

    override fun onTapSaveMedicalRecord(consultationChatVO: ConsulationChatVO, owner: LifecycleOwner) {
        mHealthCareModel.saveMedicalRecord(consultationChatVO, onSuccess = {}, onError = {
            mView?.showSnackBar("ဆေးမှတ်တမ်း မသိမ်းဆည်းနိုင်ပါ")
        })
        mView?.showSnackBar("ဆေးမှတ်တမ်း သိမ်းဆည်းပြီးပါပြီ")
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}

}