package com.padcx.healthcare.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.padcx.healthcare.delegate.CheckoutDelegate
import com.padcx.healthcare.delegate.ShippingAddressDelegate
import com.padcx.healthcare.mvp.view.CheckOutView
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
interface CheckOutPresenter: BasePresenter<CheckOutView>, CheckoutDelegate , ShippingAddressDelegate {
    fun onUiReadyCheckout( consultationChatId : String ,  owner: LifecycleOwner)
    fun onTapCheckout(prescriotionList : List<PrescriptionVO>, deliveryAddressVO: String,
                      doctorVO: DoctorVO?, patientVO: PatientVO?, total_price : String)
    fun onTapAddShipping(patientVO: PatientVO?)
}