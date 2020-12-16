package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner

import com.padcx.doctor.mvp.presenter.RegisterPresenter
import com.padcx.doctor.mvp.view.RegisterView
import com.padcx.shared.data.model.AuthenticationModel
import com.padcx.shared.data.model.DoctorModel
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.impl.AuthenticationModelImpl
import com.padcx.shared.data.model.impl.DoctorModelImpl
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import java.util.*

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
class RegisterPresenterImpl : RegisterPresenter , AbstractBaseePresenter<RegisterView>() {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val myCareModel : HealthCareModel = HealthCareModelImpl
    private val doctorModel : DoctorModel = DoctorModelImpl

    override fun onTapRegister(
        context: Context,
        username: String,
        email: String,
        password: String,
        token: String,
        speciality_name: String,
        phone: String,
        degree: String,
        biography: String
    ) {
        if(email.isEmpty() || password.isEmpty() || username.isEmpty() || speciality_name.isEmpty() ||
            phone.isEmpty() || degree.isEmpty() || biography.isEmpty()){
            mView.showError("Please enter all fields")
        } else {
            val doctorVo = DoctorVO(
                id = UUID.randomUUID().toString(),
                name = username,
                email = email,
                deviceId = token,
                biography = biography,
                degree = degree,
                phone = phone,
                speciality = speciality_name
            )

            mAuthenticationModel.register(username,email, password, onSuccess = {
                mView.navigateToToLoginScreen()
                myCareModel.registerNewDoctor(doctorVo,  onSuccess = {
                    doctorModel?.saveNewDoctorRecord(doctorVo, onSuccess = {}, onError = {})

                },onFailure = {} )

            }, onFailure = {
                mView.showError(it)
            })

        }
    }

    override fun navigateToLoginScreen() {
        mView.navigateToToLoginScreen()
    }

    override fun onUiReady(
        context: Context,
        owner: LifecycleOwner
    ) {

    }
}