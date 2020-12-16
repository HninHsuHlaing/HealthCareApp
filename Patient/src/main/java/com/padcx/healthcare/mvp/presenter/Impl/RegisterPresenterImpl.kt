package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner


import com.padcx.healthcare.mvp.presenter.RegisterPresenter
import com.padcx.healthcare.mvp.view.RegisterView
import com.padcx.shared.data.model.AuthenticationModel
import com.padcx.shared.data.model.HealthCareModel
import com.padcx.shared.data.model.PatientModel
import com.padcx.shared.data.model.impl.AuthenticationModelImpl
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.model.impl.PatientModelImpl
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter
import java.util.*

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
class RegisterPresenterImpl : RegisterPresenter,  AbstractBaseePresenter<RegisterView>()  {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val myCareModel : HealthCareModel = HealthCareModelImpl
    private val patientModel : PatientModel = PatientModelImpl

    override fun onTapRegister(
        context: Context,
        username: String,
        email: String,
        password: String,
        token: String
    ) {
        if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
            mView.showError("Please enter all fields")
        } else {

            val patientVO = PatientVO(
                id = UUID.randomUUID().toString(),
                name = username,
                email = email,
                deviceId = token
            )
            mAuthenticationModel.register(username,email, password, onSuccess = {

                myCareModel.registerNewPatient(patientVO,  onSuccess = {
                    patientModel?.saveNewPatientRecord(patientVO, onSuccess = {}, onError = {})
                    mView.navigateToLoginScreen()
                },onFailure = {} )

            }, onFailure = {
                mView.showError(it)
            })

        }
    }

    override fun navigateToLoginScreen() {
        mView.navigateToLoginScreen()
    }

    override fun onUiReady(
        context: Context,
        owner: LifecycleOwner
    ) {}
}