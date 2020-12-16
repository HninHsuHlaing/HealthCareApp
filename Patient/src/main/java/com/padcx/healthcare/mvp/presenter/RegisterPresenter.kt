package com.padcx.healthcare.mvp.presenter

import android.content.Context
import com.padcx.shared.data.vo.PatientVO

/**
 * Created by Hnin Hsu Hlaing
 * on 11/24/2020
 */
interface RegisterPresenter {
    fun onTapRegister(context: Context, patientVO: PatientVO, password: String)
    fun navigateToLoginScreen()
}