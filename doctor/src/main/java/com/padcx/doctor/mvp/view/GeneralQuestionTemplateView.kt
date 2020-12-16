package com.padcx.doctor.mvp.view

import com.padcx.shared.data.vo.GeneralQuestionTemplateVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface GeneralQuestionTemplateView  : BaseView {
    fun displayGeneralQuestions(list : List<GeneralQuestionTemplateVO>)
    fun navigateToToChatRoom(questions : String)
}