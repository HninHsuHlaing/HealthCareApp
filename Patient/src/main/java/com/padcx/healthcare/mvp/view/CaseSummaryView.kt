package com.padcx.healthcare.mvp.view

import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.data.vo.SpecialquestionVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface CaseSummaryView  : BaseView {
    fun displaySpecialQuestions(list: List<SpecialquestionVO>)
    fun displayOnceGeneralQuestion()
    fun displayAlwaysGeneralQuestion()
    fun replaceQuestionAnswerList(position : Int , questionAnswerVO: QuestionAndAnswerVO)
}