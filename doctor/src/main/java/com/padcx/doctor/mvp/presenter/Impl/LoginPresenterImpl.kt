package com.padcx.doctor.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenter.LoginPresenter
import com.padcx.doctor.mvp.view.LoginView
import com.padcx.shared.data.model.AuthenticationModel
import com.padcx.shared.data.model.impl.AuthenticationModelImpl
import com.padcx.shared.data.model.impl.DoctorModelImpl
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class LoginPresenterImpl : LoginPresenter, AbstractBaseePresenter<LoginView>() {
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mDoctorModel = DoctorModelImpl
    private val mCareModel = HealthCareModelImpl

    override fun onTapLogin(
        context: Context,
        email: String,
        password: String,
        owner: LifecycleOwner
    ) {
        if(email.isEmpty() || password.isEmpty()){
            mView.showError("Please fill all the field.")
        }else{
                mAuthenticationModel.login(email,password,onSuccess = {
                mCareModel.getDoctorByEmail(email,onSuccess = { },onFaiure = { })
                    mCareModel.getDoctorByEmailFromDB(email).observe(owner, Observer {doctor->
                        doctor?.let {
                            mView.navigateToMainScreen(doctor)
                        }
                    })
                },onFailure = {
                        mView.showError(it)
                })
        }
    }

    override fun onTapRegister() {
        mView.navigateToRegisterScreen()
    }


    override fun onUiReady(context: Context, owner: LifecycleOwner) { }
}