package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.healthcare.mvp.presenter.LoginPresenter
import com.padcx.healthcare.mvp.view.LoginView
import com.padcx.shared.data.model.impl.AuthenticationModelImpl
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class LoginPresenterImpl :LoginPresenter,AbstractBaseePresenter<LoginView>() {

    private val mAuthenticationModel = AuthenticationModelImpl
    private  val mHealthCareModel =  HealthCareModelImpl


    override fun onTapLogin(
        context: Context,
        email: String,
        password: String,
        owner: LifecycleOwner
    ) {
        if(email.isEmpty() || password.isEmpty()){
            mView.showError("Please Fill all field.")
        }else{
            mAuthenticationModel.login(email,password,onSuccess = {
            mHealthCareModel.getPatientByEmail(email, onSuccess = {
            }, onFaiure = {})

                mHealthCareModel.getPatientByEmailFromDB(email)
                    .observe(owner, Observer { patient->
                        patient?.let {
                        mView?.navigateToHomeScreen(it)
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

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }
}