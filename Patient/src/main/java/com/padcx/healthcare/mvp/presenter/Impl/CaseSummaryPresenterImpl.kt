package com.padcx.healthcare.mvp.presenter.Impl

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.healthcare.mvp.presenter.CaseSummaryPresenter
import com.padcx.healthcare.mvp.view.CaseSummaryView
import com.padcx.healthcare.util.DateUtils
import com.padcx.shared.data.model.impl.HealthCareModelImpl
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.mvp.presenter.AbstractBaseePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class CaseSummaryPresenterImpl : CaseSummaryPresenter, AbstractBaseePresenter<CaseSummaryView>() {
        private val mHealthCareModel = HealthCareModelImpl
    override fun onUiReadyforSpecialQuestion(
        context: Context,
        speciality: String,
        owner: LifecycleOwner
    ) {
        mHealthCareModel.getSpecialQuestionBySpeciality(speciality, onSuccess = {} , onError = {})

        mHealthCareModel.getSpecialQuestionBySpecialityFromDB()
            .observe(owner, Observer {
                mView?.displaySpecialQuestions(it)
            })
    }

    override fun onUiReadyforGeneralQuestion(
        context: Context,
        email: String,
        owner: LifecycleOwner
    ) {
        mHealthCareModel.getPatientByEmailFromDB(email)
            .observe(owner, Observer { patient ->
                if(patient.blood_type.isBlank())
                {
                    mView?.displayOnceGeneralQuestion()
                } else
                {
                    mView?.displayAlwaysGeneralQuestion()
                }
            })
    }

    override fun onTapSendBroadCast(
        context: Context,
        speciality: String,
        questionAnswerList: List<QuestionAndAnswerVO>,
        patientVO: PatientVO
    ) {
        speciality?.let{
            mHealthCareModel.sendBroadCastConsultationRequest(speciality,questionAnswerList,patientVO,
                DateUtils().getCurrentDate(),
                onSuccess = {} , onFailure = {})
        }
    }

    override fun navigateToNextScreen() {

    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }

    override fun onAnswerChange(position: Int, questionanswervo: QuestionAndAnswerVO) {
        mView?.replaceQuestionAnswerList(position,questionanswervo)
    }
}