package com.padcx.shared.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.padcx.shared.data.vo.converters.SenderVOConverter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/4/2020
 */
@Entity(tableName = "chat_message")
@IgnoreExtraProperties
@TypeConverters(SenderVOConverter::class)
data class MessageVO(
    @PrimaryKey
    var id : String ="",
    var sendAt: String= "",
    var sendBy: SenderVO = SenderVO(),
    var messageText: String= "",
    var messageImage: String = "",
    var type : String? = ""
)