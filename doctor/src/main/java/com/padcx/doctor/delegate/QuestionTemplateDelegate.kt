package com.padcx.doctor.delegate

import com.padcx.shared.data.vo.GeneralQuestionTemplateVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface QuestionTemplateDelegate {
    fun onTapOneQuestion(generalQuestionTemplateVO: GeneralQuestionTemplateVO)
}