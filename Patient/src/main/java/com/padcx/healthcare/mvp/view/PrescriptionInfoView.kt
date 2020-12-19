package com.padcx.healthcare.mvp.view

import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/19/2020
 */
interface PrescriptionInfoView : BaseView {
    fun displayPrescriptionList(prescription_list : List<PrescriptionVO>)
}