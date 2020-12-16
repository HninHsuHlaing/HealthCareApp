package com.padcx.shared.data.vo

import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@IgnoreExtraProperties
data class QuestionAndAnswerVO (
    var id : String ="",
    var question: String= "",
    var answer: String = ""
)