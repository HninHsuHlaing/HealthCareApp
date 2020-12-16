package com.padcx.shared.persistent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vo.MessageVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
@Dao
interface ChatMessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChatMessage(chat: MessageVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChatMessages(patientList: List<MessageVO>)

    @Query("select * from chat_message")
    fun getAllChatMessage(): LiveData<List<MessageVO>>

    @Query("select * from chat_message WHERE id = :id")
    fun getAllChatMessageDataBy(id: String): LiveData<MessageVO>

    @Query("DELETE FROM chat_message")
    fun deleteAllChatMessageData()

    @Query("DELETE FROM chat_message WHERE id = :id")
    fun deleteChatMessageById(id: String)
}