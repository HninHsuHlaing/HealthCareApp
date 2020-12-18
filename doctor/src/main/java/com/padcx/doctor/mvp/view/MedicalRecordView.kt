package com.padcx.doctor.mvp.view

import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
interface MedicalRecordView  : BaseView{
    fun showSnackBar(message : String)
}