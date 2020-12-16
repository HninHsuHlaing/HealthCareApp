package com.padcx.doctor.mvp.presenter

import com.padcx.doctor.delegate.QuestionTemplateDelegate
import com.padcx.doctor.mvp.view.GeneralQuestionTemplateView
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface GeneralQuestionTemplatePresenter: BasePresenter<GeneralQuestionTemplateView>, QuestionTemplateDelegate {
}