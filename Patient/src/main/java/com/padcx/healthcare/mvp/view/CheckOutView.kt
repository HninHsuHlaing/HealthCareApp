package com.padcx.healthcare.mvp.view

import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
interface CheckOutView : BaseView{
    fun displayPrescription(list: List<PrescriptionVO>)
    fun displayShippingAddress (list : String)
    fun displayConfirmDialog(list: List<PrescriptionVO>, total_price: String, address: String)
    fun selectedShippingAddress(address: String, previousPostion: Int)
}