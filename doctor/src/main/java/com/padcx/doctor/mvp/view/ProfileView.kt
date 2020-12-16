package com.padcx.doctor.mvp.view

import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface ProfileView : BaseView {
    fun displayDocotrData( vo : DoctorVO)
    fun hideProgressDialog()
}