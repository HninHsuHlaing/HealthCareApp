package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */

@IgnoreExtraProperties
@Entity(tableName = "recently_doctor")
data class RecentlyDoctorVO(
    @PrimaryKey
    var id: String = "",
    var name: String? = "",
    var photo: String? ="",
    var biography: String? ="",
    var degree : String? ="",
    var experience : String? = "",
    var address : String? ="",
    var speciality : String? ="",
    var email: String? ="",
    var deviceId:String? = "",
    var phone:String? ="",
    var dob : String? = "",
    var gender: String? = ""
)