package com.padcx.doctor.mvp.presenter

import android.content.Context
import com.padcx.doctor.mvp.view.RegisterView
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
interface RegisterPresenter : BasePresenter<RegisterView>{
    fun onTapRegister(context: Context, username: String, email: String, password: String, token : String, speciality_name: String,
                      phone: String, degree: String, biography : String)
    fun navigateToLoginScreen()
}