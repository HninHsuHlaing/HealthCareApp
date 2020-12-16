package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padcx.shared.data.vo.converters.DeliveryRoutineConverter
import com.padcx.shared.data.vo.converters.DoctorVOConverter
import com.padcx.shared.data.vo.converters.PatientVOConverter
import com.padcx.shared.data.vo.converters.PrescriptionVOConverter

/**
 * Created by Hnin Hsu Hlaing
 * on 11/27/2020
 */
@Entity(tableName = "checkout")
@IgnoreExtraProperties
@TypeConverters(
    PrescriptionVOConverter::class,
    DoctorVOConverter::class,
    PatientVOConverter::class,
    DeliveryRoutineConverter::class)
data class CheckoutVO(
    @PrimaryKey
    var id:String = "",
    var address:String? ="",
    var delivery_routine : DeliveryRoutineVO? = null,
    var doctor: DoctorVO? = null,
    var patientVO: PatientVO? =null,
    var prescriptionVO: ArrayList<PrescriptionVO>? = arrayListOf(),
    var totalPrice: Int? = 0
)