package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Hnin Hsu Hlaing
 * on 11/27/2020
 */
@Entity(tableName = "general_question")
@IgnoreExtraProperties
data  class GeneralQuestionTemplateVO(
        @PrimaryKey
        var id: String= "",
        var question: String = "",
        var type: String = ""
)

