package com.padcx.healthcare.mvp.presenter

import android.content.Context
import com.padcx.healthcare.mvp.view.RegisterView
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 11/24/2020
 */
interface RegisterPresenter:BasePresenter<RegisterView> {
    fun onTapRegister(context: Context, username: String, email: String, password: String, token : String)
    fun navigateToLoginScreen()
}