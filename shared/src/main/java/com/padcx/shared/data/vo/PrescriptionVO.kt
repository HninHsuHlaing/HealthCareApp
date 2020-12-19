package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padcx.shared.data.vo.converters.RoutineVOConverter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Entity(tableName = "prescription")
@IgnoreExtraProperties
@TypeConverters(RoutineVOConverter::class)
data class PrescriptionVO(
    @PrimaryKey
    var id:String ="",
    var count: String ="",
    var medicine_name :String = "",
    var routineVO: RoutineVO = RoutineVO(),
    var price: String = ""
)