package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.healthcare.mvp.presenter.PrescriptionInfoPresenter
import com.padcx.healthcare.mvp.view.PrescriptionInfoView
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/19/2020
 */
class PrescriptionInfoPresenterImpl : PrescriptionInfoPresenter, AbstractBaseePresenter<PrescriptionInfoView>() {

    private val mHealthhCareModle : HealthCareModel = HealthCareModelImpl
    lateinit var mOwner: LifecycleOwner

    override fun onUiReadyForPrescription(consulation_chat_id: String) {

        mHealthhCareModle.getPrescription(consulation_chat_id, onSuccess = {}, onError = {})

        mHealthhCareModle.getPrescriptionFromDB()
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