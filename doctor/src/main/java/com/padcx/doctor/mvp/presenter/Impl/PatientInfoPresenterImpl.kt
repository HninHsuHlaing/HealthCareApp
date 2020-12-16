package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenter.PatientInfoPresenter
import com.padcx.doctor.mvp.view.PatientInfoView
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import com.padcx.shared.util.DateUtil

/**
 * Created by Hnin Hsu Hlaing
 * on 12/12/2020
 */
class PatientInfoPresenterImpl : PatientInfoPresenter, AbstractBaseePresenter<PatientInfoView>() {
    private  val mHealthCareModel = HealthCareModelImpl
    lateinit var mOwner: LifecycleOwner
    override fun onTapStartConsultation(consultationRequestVO: ConsulationRequestVO) {
        mHealthCareModel.startConsultation(consultationRequestVO.cr_id, DateUtil().getCurrentDate(),
                consultationRequestVO.caseSummary,
                consultationRequestVO.patient, consultationRequestVO.doctor,
                onSuccess = {} , onFailure = {})

        mHealthCareModel.getConsultationByConsulationRequestId(consultationRequestVO.cr_id, onSuccess = {}, onFailure ={})
        mHealthCareModel.getConsultationByConsulationRequestIdFromDB(consultationRequestVO.cr_id)
                .observe(mOwner, Observer {
                    it?.let{
                        mView?.nextPageToChat(it.cr_id.toString())
                    }
                })
    }

    override fun onUiReadyConstulation(consulationRequestId: String, owner: LifecycleOwner) {
        mOwner = owner
        mHealthCareModel.getConsultationByConsulationRequestIdFromDB(consulationRequestId)
                .observe(owner, Observer {
                    it?.let{
                        mView?.displayPatientInfo(it)
                    }

                })
    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }
}