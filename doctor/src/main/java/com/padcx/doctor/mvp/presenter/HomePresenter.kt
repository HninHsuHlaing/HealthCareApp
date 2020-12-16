package com.padcx.doctor.mvp.presenter

import com.padcx.doctor.delegate.ConsultationAcceptDelegate
import com.padcx.doctor.delegate.ConsultationRequestDelegate
import com.padcx.doctor.mvp.view.HomView
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface HomePresenter :BasePresenter<HomView>, ConsultationRequestDelegate,ConsultationAcceptDelegate{

}