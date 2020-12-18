package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenter.ProfilePresenter
import com.padcx.doctor.mvp.view.ProfileView
import com.padcx.doctor.util.SessionManager
import com.padcx.shared.data.model.AuthenticationModel
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.AuthenticationModelImpl
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class ProfilePresenterImpl  : ProfilePresenter, AbstractBaseePresenter<ProfileView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mHealthCareModel : HealthCareModel = HealthCareModelImpl

    override fun onUiReadyForProfile(context: Context, owner: LifecycleOwner) {
        mHealthCareModel.getDoctorByEmail(SessionManager.doctor_email.toString(),onSuccess = {}, onFaiure = {})
        mHealthCareModel.getDoctorByEmailFromDB(SessionManager.doctor_email.toString())
                .observe(owner, Observer { data ->
                    data?.let {
                        mView?.displayDocotrData(data) }
                })
    }

    override fun updateUserData(bitmap: Bitmap,
                                specialitname: String, phone: String,
                                degree: String, bigraphy: String,
                                address: String, experience: String,
                                dateofbirth: String, gender: String)
    {
        mHealthCareModel.uploadPhotoToFirebaseStorage(bitmap,
                onSuccess = {
                    mAuthenticationModel.updateProfile(it,onSuccess = {}, onFailure = {})

                    mView?.hideProgressDialog()

                    var doctorVO = DoctorVO(
                            id= SessionManager.doctor_id.toString(),
                            deviceId = SessionManager.doctor_deviceId.toString(),
                            name = SessionManager.doctor_name.toString(),
                            email = SessionManager.doctor_email.toString(),
                            photo = it,
                            speciality = specialitname,
                            phone = phone,
                            degree = degree,
                            biography = bigraphy,
                            address = address,
                            experience = experience,
                            gender =  gender,
                            dob = dateofbirth
                    )
                    mHealthCareModel.addDoctorInfo(doctorVO,onSuccess = {}, onError = {})
                },
                onFailure = {
                   // mView?.showError("Profile Update Failed")
                })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {}
}
