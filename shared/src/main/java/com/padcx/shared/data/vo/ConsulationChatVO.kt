package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padcx.shared.data.vo.converters.*

/**
 * Created by Hnin Hsu Hlaing
 * on 11/27/2020
 */
@Entity(tableName = "consulation_chat")

@IgnoreExtraProperties
@TypeConverters(PatientVOConverter::class,DoctorVOConverter::class,CaseSummaryConverter::class)
data class ConsulationChatVO(
        @PrimaryKey
    var id: String= "",
        var patient: PatientVO = PatientVO(),
        var doctor: DoctorVO = DoctorVO(),
        var caseSummary:  ArrayList<QuestionAndAnswerVO>? = arrayListOf(),
        var patient_id : String? = "",
        var status : Boolean = false,
        var doctor_id :String? = "",
        var start_consultation_date : String ?= "",
        var medical_record :String ? = ""
//        var finish_consultation_status : Boolean = false
)
