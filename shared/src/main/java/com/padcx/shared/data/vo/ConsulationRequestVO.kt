package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padcx.shared.data.vo.converters.CaseSummaryConverter
import com.padcx.shared.data.vo.converters.DoctorVOConverter
import com.padcx.shared.data.vo.converters.PatientVOConverter
import com.padcx.shared.data.vo.converters.TimeStampCOnverter

/**
 * Created by Hnin Hsu Hlaing
 * on 11/27/2020
 */

@Entity(tableName = "consulation_request")
@IgnoreExtraProperties
@TypeConverters(
    CaseSummaryConverter::class,
    PatientVOConverter::class,
    TimeStampCOnverter::class,
    DoctorVOConverter::class)
data class ConsulationRequestVO(
        @PrimaryKey
    var cr_id: String= "",
        var patient: PatientVO  = PatientVO(),
        var doctor :DoctorVO = DoctorVO(),
        var speciality : String ?= "",
        var date_time : String?= null,
        var caseSummary : ArrayList<QuestionAndAnswerVO> = arrayListOf(),
        var status : String? = "none",
        var p_id : String = "",
        var d_id : String = "",
        var consultation_id : String = ""
)