package com.padcx.doctor.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.mvp.view.LoginView
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface LoginPresenter :BasePresenter<LoginView> {
    fun onTapLogin(context: Context, email: String, password: String, owner: LifecycleOwner)
    fun onTapRegister()
}