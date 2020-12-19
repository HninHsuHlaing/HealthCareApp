package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenter.PrescriptionInfoPresenter
import com.padcx.doctor.mvp.view.PrescriptionInfoView
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/19/2020
 */
class PrescriptionInfoPresenterImpl : PrescriptionInfoPresenter, AbstractBaseePresenter<PrescriptionInfoView>() {

    private val mHealthCareModel :HealthCareModel = HealthCareModelImpl
    lateinit var mOwner: LifecycleOwner
    override fun onUiReadyForPrescription(consulation_chat_id: String) {

        mHealthCareModel.getPrescription(consulation_chat_id, onSuccess = {}, onError = {})

        mHealthCareModel.getPrescriptionFromDB()
                .observe(mOwner, Observer {
                    it?.let{
                        mView?.displayPrescriptionList(it)
                    }
                })

    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner = owner
    }

}