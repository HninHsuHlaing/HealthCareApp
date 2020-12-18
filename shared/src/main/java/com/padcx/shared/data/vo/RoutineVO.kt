package com.padcx.shared.data.vo

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
@IgnoreExtraProperties
data class RoutineVO(
//    var day: String? = "",
//    var note: String? = "",
//    var repeat: String? = "",
//    var tab: String? = "",
//    var times: Timestamp? = null
        var id: String= "",
        var amount: String? = "",
        var days: String ?="",
        var comment: String? = "",
        var repeat: String ?="",
        var quantity: String? = "",
        var times: String ?=""
)