package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@IgnoreExtraProperties
@Entity(tableName = "medicine")
data class FrequentlyMedicineVO(
    @PrimaryKey
    var id: String,
    var name: String? = "",
    var price: Int? = 0
)