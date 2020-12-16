package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.healthcare.mvp.presenter.ProfilePresenter
import com.padcx.healthcare.mvp.view.ProfileView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.data.model.AuthenticationModel
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.AuthenticationModelImpl
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class ProfilePresenterImpl : ProfilePresenter, AbstractBaseePresenter<ProfileView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mHealthCareModel : HealthCareModel = HealthCareModelImpl

    override fun onUiReadyForAccountFragment(context: Context, owner: LifecycleOwner) {
        mHealthCareModel.getPatientByEmail(SessionManager.patient_email.toString(),onSuccess = {}, onFaiure = {})
        mHealthCareModel.getPatientByEmailFromDB(SessionManager.patient_email.toString())
                .observe(owner, Observer { patient ->
                    patient?.let {
                        mView?.displayPatientData(patient) }
                })
    }


    override fun updateUserData(
            bitmap: Bitmap,
            blood_type: String,
            dateofbirth: String,
            height: String,
            comment: String,
            phone: String
    ) {

        mHealthCareModel.uploadPhotoToFirebaseStorage(bitmap,
                onSuccess = {
                    mAuthenticationModel.updateProfile(it,onSuccess = {}, onFailure = {})

                    mView?.hideProgressDialog()

                    var patientVo = PatientVO(
                            id= SessionManager.patient_id.toString(),
                            deviceId = SessionManager.patient_deviceId.toString(),
                            name = SessionManager.patient_name.toString(),
                            email = SessionManager.patient_email.toString(),
                            photo = it,
                            blood_type = blood_type,
                            blood_pressure =SessionManager.patient_bloodPressure.toString(),
                            dob = dateofbirth,
                            weight = SessionManager.patient_weight.toString(),
                            height = height,
                            allergic_medicine = comment
                            // = phone
                    )
                    mHealthCareModel.addPatientInfo(patientVo,onSuccess = {}, onError = {})
                },
                onFailure = {
                   // mView?.showError("Profile Update Failed")
                })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }
}

