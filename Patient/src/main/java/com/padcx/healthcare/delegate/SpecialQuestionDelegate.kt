package com.padcx.healthcare.delegate

import com.padcx.shared.data.vo.QuestionAndAnswerVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface SpecialQuestionDelegate {
    fun onAnswerChange(position: Int, questionanswervo : QuestionAndAnswerVO)
}