package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.healthcare.mvp.presenter.CheckOutPresenter
import com.padcx.healthcare.mvp.view.CheckOutView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class CheckOutPresenterImpl : CheckOutPresenter, AbstractBaseePresenter<CheckOutView>() {

    private val mHealthCareModel : HealthCareModel = HealthCareModelImpl
    private lateinit var mOwner : LifecycleOwner


    override fun onUiReadyCheckout(consultationChatId: String, owner: LifecycleOwner) {

        mHealthCareModel.getPrescription(consultationChatId, onSuccess = {}, onError = {})

        mHealthCareModel.getPrescriptionFromDB()
                .observe(owner, Observer {
                    it?.let{
                        mView?.displayPrescription(it)
                    }
                })
        mHealthCareModel.getPatientByEmail(SessionManager.patient_email.toString(), onSuccess = {}, onFaiure = {})

        mHealthCareModel.getPatientByEmailFromDB(SessionManager.patient_email.toString())
                .observe(owner, Observer {
                    it?.let{
                        SessionManager.addPatientInfo(it)
                        mView?.displayShippingAddress(it.perment_address)
                    }
                })
    }

    override fun onTapCheckout(prescriotionList: List<PrescriptionVO>, deliveryAddressVO: String, doctorVO: DoctorVO?, patientVO: PatientVO?, total_price: String) {
        if (doctorVO != null && patientVO != null) {
            mHealthCareModel.checkout(prescriotionList,
                    deliveryAddressVO,
                    doctorVO,
                    patientVO,
                    total_price,
                    onSuccess = {}, onFailure = {})
            mView?.displayConfirmDialog(prescriotionList,deliveryAddressVO,total_price)
        }
    }

    override fun onTapAddShipping(patientVO: PatientVO?) {

    }

//    override fun onTapCheckout(prescriotionList: List<Any>, deliveryAddressVO: String, doctorVO: DoctorVO?, patientVO: PatientVO?, total_price: String) {
//
//    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mOwner= owner
    }

    override fun onTapSelected(address: String, previousPosition: Int) {
        mView?.selectedShippingAddress(address,previousPosition)
    }

}