package com.padcx.shared.data.vo.converters

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padcx.shared.util.CONSULTATED_PATIENT

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
@Entity(tableName = CONSULTATED_PATIENT)
@IgnoreExtraProperties
class ConsultatedPatientVO(
    @PrimaryKey
    var id: String= "",
    var patient_id: String ?= ""
)