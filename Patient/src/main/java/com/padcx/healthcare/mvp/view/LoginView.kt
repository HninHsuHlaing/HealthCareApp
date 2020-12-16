package com.padcx.healthcare.mvp.view

import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface LoginView :BaseView{
    fun navigateToHomeScreen(patientVO: PatientVO)
    fun navigateToRegisterScreen()
}