package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Entity(tableName = "special_question")
@IgnoreExtraProperties
data class SpecialquestionVO(
        @PrimaryKey
    var id: String= "",
        var question: String = ""
)