package com.padcx.healthcare.mvp.presenter

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padcx.healthcare.delegate.QuestionAndAnswerDelegate
import com.padcx.healthcare.delegate.SpecialQuestionDelegate
import com.padcx.healthcare.mvp.view.CaseSummaryView
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface CaseSummaryPresenter : BasePresenter<CaseSummaryView>, SpecialQuestionDelegate, QuestionAndAnswerDelegate{
    fun onUiReadyforSpecialQuestion(context: Context, speciality: String, owner: LifecycleOwner)
    fun onUiReadyforGeneralQuestion(context: Context, email: String, owner: LifecycleOwner)
    fun onTapSendBroadCast(context: Context, speciality: String, questionAnswerList: List<QuestionAndAnswerVO>, patientVO: PatientVO)
    fun navigateToNextScreen()
}