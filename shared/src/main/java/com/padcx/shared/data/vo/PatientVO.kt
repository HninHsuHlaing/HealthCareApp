package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padcx.shared.data.vo.converters.TimeStampCOnverter


/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
@Entity(tableName = "patient")

@IgnoreExtraProperties
@TypeConverters(TimeStampCOnverter::class)
data class PatientVO(
    @PrimaryKey
    var id:String= "" ,
    var name:String = "",
    var photo: String? = "",
    var dob : String? = "",
    var blood_type : String ="",
    var blood_pressure: String ="",
    var email: String ="",
    var deviceId:String? ="",
    var height : String? ="",
    var weight : String? ="",
    var allergic_medicine : String? =""
    //var created_date : Timestamp? = null
)
