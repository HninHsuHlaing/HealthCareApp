package com.padcx.healthcare.mvp.presenter

import android.content.Context
import com.padcx.healthcare.delegate.ConsultationAcceptDelegate
import com.padcx.healthcare.delegate.RecentlyDoctorViewItemDelegate
import com.padcx.healthcare.delegate.SpecilitiesViewItemDelegate
import com.padcx.healthcare.mvp.view.HomeView
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.SpecialitiesVO
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/5/2020
 */
interface HomePresenter : BasePresenter<HomeView>
    , RecentlyDoctorViewItemDelegate , SpecilitiesViewItemDelegate , ConsultationAcceptDelegate{
    fun onTapSpeciality(context: Context, specialitiesVO: SpecialitiesVO)
    fun navigateToNextScreen()
    fun statusUpdateForCompleteType(context: Context, cosultation_chat_id : String, consultationRequestVO: ConsulationRequestVO)
   // fun onReadyStage(lifecycleOwner: LifecycleOwner)
}