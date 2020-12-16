package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties


/**
 * Created by Hnin Hsu Hlaing
 * on 11/27/2020
 */

@Entity(tableName = "specialites")
data class SpecialitiesVO(
    @PrimaryKey
    var id: String= "",
    var name: String = "",
    var photo: String = ""

)
